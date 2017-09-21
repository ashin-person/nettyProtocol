package ljx.ashin.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * SmartCar自定义协议的编码器
 * Created by Ashin Liang on 2017/9/21.
 */
public class SmartCarEncoder extends MessageToByteEncoder<SmartCarProtocol>{

    protected void encode(ChannelHandlerContext channelHandlerContext,
                          SmartCarProtocol smartCarProtocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeByte(smartCarProtocol.getHeaderStartFlag());
        byteBuf.writeByte(smartCarProtocol.getLength());
        byteBuf.writeBytes(smartCarProtocol.getContent());

    }
}
