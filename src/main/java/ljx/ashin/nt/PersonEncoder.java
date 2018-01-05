package ljx.ashin.nt;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器,即序列化
 * 将object转换成Byte[]
 * Created by Ashin Liang on 2018/1/5.
 */
public class PersonEncoder extends MessageToByteEncoder<Person> {
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          Person person, ByteBuf byteBuf) throws Exception {
        //1、将person对象转换为byte[]
        byte[] bytes = ConverterUtils.ObjToBytes(person);
        //2、将byte[]的内容写入到byteBuf中
        byteBuf.writeBytes(bytes);
        //3、将数据刷出去
        channelHandlerContext.flush();
    }
}
