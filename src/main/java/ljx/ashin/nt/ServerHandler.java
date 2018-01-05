package ljx.ashin.nt;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Ashin Liang on 2018/1/5.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead读取可以端的信息");
        if (null!=msg&& msg instanceof Person){
            Person person = (Person)msg;
            System.out.println("客户端传送过来的数据为:"+person.getName());
        }
        System.out.println("调用channelRead完毕");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server 读取数据完毕..");
        ctx.flush();//刷新后才将数据发出到SocketChannel
    }
}
