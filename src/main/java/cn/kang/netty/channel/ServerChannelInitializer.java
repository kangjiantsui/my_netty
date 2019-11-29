package cn.kang.netty.channel;

import cn.kang.common.protocol.PersonMessage;
import cn.kang.common.protocol.UserMsg;
import cn.kang.netty.handler.ServerFrameHandler;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * 可以同时处理http和websocket请求的channel
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        // HTTP请求的解码和编码
        pipeline.addLast(new HttpServerCodec());
        // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
        // 原因是HTTP解码器会在每个HTTP消息中生成多个消息对象HttpRequest/HttpResponse,HttpContent,LastHttpContent
        pipeline.addLast(new HttpObjectAggregator(65536));
        // 主要用于处理大数据流，比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的; 增加之后就不用考虑这个问题了
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new LengthFieldBasedFrameDecoder(16777215, 0, 4, 0, 4));
        pipeline.addLast(new LengthFieldPrepender(4));
//        pipeline.addLast(new SimpleChannelInboundHandler() {
//
//            @Override
//            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//                super.channelRegistered(ctx);
//                connectNum.addAndGet(1);
//                System.out.println("连接接入,目前连接数:" + connectNum);
//            }
//
//            @Override
//            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//                super.channelUnregistered(ctx);
//                connectNum.addAndGet(-1);
//                System.out.println("连接断开,目前连接数:" + connectNum);
//            }
//
//            @Override
//            protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//                if (msg instanceof FullHttpRequest) {
//                    handleHttpRequest(ctx, (FullHttpRequest) msg);
//                } else if (msg instanceof WebSocketFrame) {
//                    handleWebSocketFrame(ctx, (WebSocketFrame) msg);
//                }
//            }
//
//            // 处理HTTP的代码
//            private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws UnsupportedEncodingException {
//                // 如果是websocket请求就握手升级
//                if ("/ws".equalsIgnoreCase(req.uri())) {
//                    System.out.println("websocket 请求接入");
//                    WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("/ws", null, false);
//                    handshaker = wsFactory.newHandshaker(req);
//                    handshaker.handshake(ctx.channel(), req);
//                }
//            }
//
//            // 处理Websocket的代码
//            private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws InvalidProtocolBufferException, InterruptedException {
//                if (frame instanceof CloseWebSocketFrame) {
//                    ctx.close().sync();
//                } else if (frame instanceof PingWebSocketFrame) {
//                    ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
//                } else {
//                    ByteBuf buf = frame.content();
//                    byte[] bytes = new byte[buf.readableBytes()];
//                    buf.readBytes(bytes);
//                    buf.retain();
//                    PersonMessage.Person person = PersonMessage.Person.parseFrom(bytes);
//                    System.out.println(person.getId());
//                    System.out.println(person.getName());
//                    System.out.println("连接数:" + connectNum);
//                    ctx.writeAndFlush(frame);
//                }
//            }
//        });

        // WebSocket数据压缩
        pipeline.addLast(new WebSocketServerCompressionHandler());
        // 协议包长度限制
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true));
        //pipeline.addLast(new HttpHandler());
        // 协议包解码
        pipeline.addLast(new MessageToMessageDecoder<WebSocketFrame>() {
            @Override
            protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> objs) {
                ByteBuf buf = frame.content();
                objs.add(buf);
                buf.retain();
            }
        });
        // 协议包编码
        pipeline.addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
            @Override
            protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) {
                ByteBuf result = null;
                if (msg instanceof MessageLite) {
                    result = wrappedBuffer(((MessageLite) msg).toByteArray());
                }
                if (msg instanceof MessageLite.Builder) {
                    result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
                }

                // ==== 上面代码片段是拷贝自TCP ProtobufEncoder 源码 ====
                // 然后下面再转成websocket二进制流，因为客户端不能直接解析protobuf编码生成的

                assert result != null;
                WebSocketFrame frame = new BinaryWebSocketFrame(result);
                out.add(frame);
            }
        });

        // 协议包解码时指定Protobuf字节数实例化为CommonProtocol类型
        pipeline.addLast(new ProtobufDecoder(PersonMessage.Person.getDefaultInstance()));

        // websocket定义了传递数据的6中frame类型
        pipeline.addLast(new ServerFrameHandler());


    }

    public static class MyDecoder extends ProtobufDecoder {

        public MyDecoder(MessageLite prototype, ExtensionRegistry extensionRegistry) {
            super(prototype, extensionRegistry);
        }

        @Override
        protected void decode(ChannelHandlerContext arg0, ByteBuf arg1, List<Object> arg2) throws Exception {
            try {
                super.decode(arg0, arg1, arg2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static ExtensionRegistry getExtensionRegistery() {
            ExtensionRegistry er = ExtensionRegistry.newInstance();
            UserMsg.registerAllExtensions(er);
            return er;
        }
    }
}
