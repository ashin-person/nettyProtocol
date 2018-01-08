package ljx.ashin.sendAndRsp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Ashin Liang on 2018/1/8.
 */
public class SREncoderClientHandler extends SimpleChannelInboundHandler<SRObject> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        SRObject srObject = new SRObject();
        srObject.setMsg("channelActive msg");
        byte[] bytes = SRConvertUtils.ObjToBytes(srObject);
        ByteBuf resp = Unpooled.copiedBuffer(bytes);
        ctx.write(resp);
        ctx.flush();
    }

    protected void messageReceived(ChannelHandlerContext channelHandlerContext,
                                   SRObject srObject) throws Exception {
        System.out.println("SREncoderClientHandler");
        System.out.println("获取到的服务器信息:"+srObject.getMsg());
//        channelHandlerContext.writeAndFlush(srObject)
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端的channelRead");
        //服务端返回消息后
        SRObject object = (SRObject)msg;
        System.out.println("channelRead 接收到服务器的信息:"+object.getMsg());
        //服务端返回消息后
      /*  ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端数据为 :" + body);*/
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
