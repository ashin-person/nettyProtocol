package ljx.ashin.sendAndRsp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Ashin Liang on 2018/1/8.
 */
public class SRDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("解码器ObjDecoder开始");
        SRObject srObject = null;
        byte[] bytes = SRConvertUtils.byteBufToBytes(byteBuf);
        if (bytes!=null){
            srObject = (SRObject) SRConvertUtils.bytesToObj(bytes);
            if (null!=srObject){
                System.out.println(srObject.getMsg());
                System.out.println(srObject.getCode());
                list.add(srObject);
            }else {
                SRObject object = new SRObject();
                object.setCode("22");
                object.setMsg("解码不出东西");
                list.add(object);
            }

        }else {
            SRObject object = new SRObject();
            object.setCode("22");
            object.setMsg("解码不出东西");
            list.add(object);
        }

    }
}
