package cn.kang.netty.chatroom.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatRoomServerApp {
    public static void main(String[] args) throws InterruptedException {
        /*
        创建两个NioEventLoopGrop实例,实际上他们就是Reactor线程组
        一个用于服务端接收客户端的连接,另一个用于进行SocketChannel的网络读写
         */
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();         //她是Netty用于启动NIO服务端的辅助启动类,目的是降低服务端的开发复杂度
            serverBootstrap.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)                   //功能对应JDK_NIO类库中的ServerSocketChannel类
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new MessageEncoder(), new ServerTransferMsgHandler(), new ServerMsgHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024 * 10)       //配置TCP参数
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //调用bind方法绑定监听端口,返回一个ChannelFuture,主要用于异步操作的通知回调
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            System.out.println("出错了");
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
