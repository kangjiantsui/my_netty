package cn.kang.chatroom.handler;

import cn.kang.chatroom.Message;
import cn.kang.chatroom.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class ServerTransferMsgHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        String totalMsg = in.readCharSequence(in.readableBytes(), Charset.forName("UTF-8")).toString();
        String[] content = totalMsg.split("~");
        out.add(new Message(content[0], Utils.parseDateTime(content[1]), content[2]));
    }
}
