package ljx.ashin.sendAndRsp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Ashin Liang on 2018/1/8.
 */
public class SREncoder extends MessageToByteEncoder<SRObject> {

    protected void encode(ChannelHandlerContext channelHandlerContext,
                          SRObject srObject, ByteBuf byteBuf) throws Exception {
        System.out.println("encode");
        byte[] bytes = SRConvertUtils.ObjToBytes(srObject);
        byteBuf.writeBytes(bytes);
        channelHandlerContext.flush();//输出
    }

    protected void encode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {

    }
}
