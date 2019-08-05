package cn.kang.netty.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx)  {
        ByteBuf timeBuf = ctx.alloc().buffer();
        timeBuf.writeBytes(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).getBytes());

        ChannelFuture channelFuture = ctx.writeAndFlush(timeBuf);
        channelFuture.addListener(future -> {
            assert channelFuture == future;

            //ctx.close();
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        //Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
