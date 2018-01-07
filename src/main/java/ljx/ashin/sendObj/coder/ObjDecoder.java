package ljx.ashin.sendObj.coder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
        Person person = null;
//        byteBuf.read
        byte[] bytes = ConvertUtils.byteBufToBytes(byteBuf);
        Object object = ConvertUtils.bytesToObj(bytes);
        if (object!=null&& object instanceof Person){
            person = (Person) ConvertUtils.bytesToObj(bytes);
            System.out.println(person.getName());
            System.out.println(person.getSex());
            list.add(person);
        }else {
            list.add(new Person());
            System.out.println("解码器ObjDecoder结束");
        }


    }
}
