package cn.kang.netty.handler;

import cn.kang.common.protocol.PersonMessage;
import cn.kang.common.protocol.SglMsg;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

//处理文本协议数据，处理TextWebSocketFrame类型的数据，websocket专门处理文本的frame就是TextWebSocketFrame
@Slf4j
public class ServerFrameHandler extends SimpleChannelInboundHandler<PersonMessage.Person> {

    //读到客户端的内容并且向客户端去写内容
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonMessage.Person msg)  {
        // channelGroup.add();

        Channel channel = ctx.channel();
        log.info(msg.getId()+":"+msg.getName());
        SglMsg.SglRespMsg.Builder rb = SglMsg.SglRespMsg.newBuilder().setStatus(SglMsg.ProtoMsgStatus.PB_STATUS_OK);
        channel.writeAndFlush(rb);
    }

    //每个channel都有一个唯一的id值
    @Override
    public void handlerAdded(ChannelHandlerContext ctx)  {
        //打印出channel唯一值，asLongText方法是channel的id的全名
        System.out.println("handlerAdded："+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx)  {
        System.out.println("handlerRemoved：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        System.out.println("异常发生");
        cause.printStackTrace();
        ctx.close();
    }
}
