package ljx.reflect.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Ashin Liang on 2017/12/19.
 */
public class ReflectClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接服务器,开始发送数据...");
        byte[] bytes = "客户端已经连接上服务器".getBytes();
        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client读取服务器的信息...");
        //服务端返回信息后
        ByteBuf byteBuf = (ByteBuf)msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String body = new String(bytes,"utf-8");
        System.out.println("读取到的服务器送过来的信息为:"+body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端发生了异常");
        //释放资源
        ctx.close();
    }

    protected void messageReceived(ChannelHandlerContext channelHandlerContext,
                                   ByteBuf byteBuf) throws Exception {

    }
}
