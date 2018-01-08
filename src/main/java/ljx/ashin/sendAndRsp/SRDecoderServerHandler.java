package ljx.ashin.sendAndRsp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * Created by Ashin Liang on 2018/1/8.
 */
public class SRDecoderServerHandler extends SimpleChannelInboundHandler<SRObject> {
    protected void messageReceived(ChannelHandlerContext channelHandlerContext,
                                   SRObject srObject) throws Exception {
        System.out.println("SRDecoderServerHandler");
        System.out.println("获取到数据:"+srObject.getMsg());
        srObject.setMsg("服务器发送到客户端的内容");
        channelHandlerContext.write(srObject);
        channelHandlerContext.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead");
        SRObject srObject = (SRObject) msg;
        System.out.println("获取到数据:"+srObject.getMsg());
        srObject.setMsg("服务器发送到客户端的内容");
        SRObject serverMsg = new SRObject();
        serverMsg.setMsg("ok,i have get msg");
        byte[] bytes = SRConvertUtils.ObjToBytes(serverMsg);
        //向客户端写数据
        System.out.println("server向client发送数据");
        String currentTime = new Date(System.currentTimeMillis()).toString();
        ByteBuf resp = Unpooled.copiedBuffer(bytes);
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");

        ctx.flush();
    }
}
