package ljx.reflect.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Ashin Liang on 2017/12/19.
 */
public class ReflectServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("读取数据...");
        //读数据,byteBuf是netty中的默认传递数据的方式
        ByteBuf byteBuf = (ByteBuf)msg;
        //将byteBuf转换为String
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);//将byteBuf中的数据读入到bytes
        String body = new String(bytes,"utf-8");
        System.out.println("接收到客户端的数据:"+body);

        //向客户端写数据
        System.out.println("server向客户端写数据");
        ByteBuf rspMsg = Unpooled.copiedBuffer("服务器端成功处理了任务".getBytes());
        ctx.write(rspMsg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读取数据完毕");
        ctx.flush();//刷新后才将数据发出到SocketChannel
    }
}
