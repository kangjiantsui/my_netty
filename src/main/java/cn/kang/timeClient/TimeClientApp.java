package cn.kang.timeClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClientApp {
    public void run() throws InterruptedException {
        NioEventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(workLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler((new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    }))
                    .option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = clientBootstrap.connect("localhost", 8080).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            workLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TimeClientApp().run();
    }
}
