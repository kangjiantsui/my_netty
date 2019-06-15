package cn.kang.chatroom.server;


import cn.kang.chatroom.common.Message;
import cn.kang.chatroom.common.Utils;
import cn.kang.chatroom.common.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;
import java.util.Scanner;

public class ServerMsgHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("jsbintask-client 进入聊天室");
        Message message = new Message(Constants.SERVER, new Date(), "Hello, I'm jsbintask-server side.");
        ByteBuf buffer = ctx.alloc().buffer();
        String content = Utils.encodeMsg(message);
        buffer.writeBytes(content.getBytes());

        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg1) throws Exception {
        try {
            Message msg = (Message) msg1;
            Utils.printMsg(msg);
            Scanner scanner = new Scanner(System.in);
            System.out.println("jsbintask-server,please input msg");
            String reply = scanner.nextLine();

            Message message = new Message(Constants.SERVER, new Date(), reply);
            ctx.writeAndFlush(message);
        } finally {
            ReferenceCountUtil.release(msg1);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
