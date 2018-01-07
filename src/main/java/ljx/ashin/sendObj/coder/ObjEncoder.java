package ljx.ashin.sendObj.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import ljx.ashin.sendObj.bean.Person;
import ljx.ashin.sendObj.utils.ConvertUtils;

import java.io.ByteArrayInputStream;

/**
 * Created by AshinLiang on 2018/1/1.
 */
public class ObjEncoder extends MessageToByteEncoder<Person> {

    protected void encode(ChannelHandlerContext channelHandlerContext,
                          Person person, ByteBuf byteBuf) throws Exception {
        //将object转换byte[]
//        ByteArrayInputStream
        System.out.println("ObjEncoder编码器");
        byte[] bytes = ConvertUtils.ObjToBytes(person);
        byteBuf.writeBytes(bytes);
        channelHandlerContext.flush();//输出
//        channelHandlerContext

    }
}
