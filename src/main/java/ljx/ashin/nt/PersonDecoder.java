package ljx.ashin.nt;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 将byte[]转换为Object对象,即反序列化
 * Created by Ashin Liang on 2018/1/5.
 */
public class PersonDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] bytes = ConverterUtils.byteBufToBytes(byteBuf);
        if (bytes!=null){
            Person person =(Person)ConverterUtils.bytesToObject(bytes);
            if (null!=person.getName()){
                System.out.println("获取到的person对象,"+person.getName());
                list.add(person);
            }

        }

    }
}
