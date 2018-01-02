package ljx.ashin.sendObj.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ljx.ashin.sendObj.bean.Person;

/**
 * Created by AshinLiang on 2018/1/2.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器开始读取数据");
        Person person = (Person)msg;
        System.out.println(person.getName());


    }
}
