package ljx.ashin.sendObj.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import ljx.ashin.sendObj.bean.Person;

/**
 * Created by AshinLiang on 2018/1/2.
 */
public class ServerHandler extends SimpleChannelInboundHandler<Person> {
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Person person) throws Exception {
        System.out.println(person.getName());
        String rspMsg = "服务器已经接受到客户端的信息，正在处理中，请稍后";
        person.setName(rspMsg);
        channelHandlerContext.writeAndFlush(person);
    }
    /*@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器开始读取数据");
        Person person = (Person)msg;
        System.out.println(person.getName());
        person.setName("已经处理完数据啦");
        ctx.writeAndFlush(person);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server 读取数据完毕..");
        ctx.flush();
    }*/
}
