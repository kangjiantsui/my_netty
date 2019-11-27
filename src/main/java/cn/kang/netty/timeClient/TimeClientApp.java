package cn.kang.netty.timeClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class TimeClientApp {
    public static void main(String[] args) {

    }

    public void run() throws InterruptedException {
        new Bootstrap().group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler((new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                try {
                                    ByteBuf byteBuf = (ByteBuf) msg;
                                    int length = byteBuf.readableBytes();
                                    byte[] buff = new byte[1024];
                                    byteBuf.readBytes(buff, 0, length);
                                    System.out.println("current time: " + new String(buff, 0, length));
                                    ctx.close();
                                } finally {
                                    ReferenceCountUtil.release(msg);
                                }
                            }
                        });
                    }
                }))
                .option(ChannelOption.SO_KEEPALIVE, true)
                .connect("localhost", 8080)
                .sync()
                .channel()
                .closeFuture()
                .sync();

    }
}
