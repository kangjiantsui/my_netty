package cn.kang.chatroom;

import cn.kang.chatroom.handler.ServerMsgHandler;
import cn.kang.chatroom.handler.ServerTransferMsgHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatRoomServerApp {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new MessageEncoder(), new ServerTransferMsgHandler(), new ServerMsgHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024 * 10)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            System.out.println("出错了");
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
