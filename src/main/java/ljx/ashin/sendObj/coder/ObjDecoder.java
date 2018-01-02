package ljx.ashin.sendObj.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import ljx.ashin.sendObj.bean.Person;
import ljx.ashin.sendObj.utils.ConvertUtils;

import java.util.List;

/**
 * Created by AshinLiang on 2018/1/2.
 */
public class ObjDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("解码器ObjDecoder开始");
        byte[] bytes = byteBuf.array();
        Person person = (Person) ConvertUtils.bytesToObj(bytes);
        System.out.println(person.getName());
        System.out.println(person.getSex());
        list.add(person);
        System.out.println("解码器ObjDecoder结束");

    }
}
