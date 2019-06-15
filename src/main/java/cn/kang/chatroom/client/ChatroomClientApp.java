package cn.kang.chatroom.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatroomClientApp {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(workLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        // 向pipeline中添加编码、解码、业务处理的handler
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientTransferMsgHandler(), new ClientMsgHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);
            // 链接服务器
            ChannelFuture channelFuture = clientBootstrap.connect("localhost", 8888).sync();
            //将request对象写入outbundle处理后发出（即RpcEncoder编码器）
            channelFuture.channel().closeFuture().sync();
        } finally {
            workLoopGroup.shutdownGracefully();
        }
    }
}
