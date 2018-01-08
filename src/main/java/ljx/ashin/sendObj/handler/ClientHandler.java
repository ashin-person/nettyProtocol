package ljx.ashin.sendObj.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ljx.ashin.sendObj.bean.Person;
import ljx.ashin.sendObj.utils.ConvertUtils;

/**
 * Created by AshinLiang on 2018/1/3.
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 连接服务器之后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
      /*  Person p = new Person();
        p.setName("小花");
        byte[] bytes = ConvertUtils.ObjToBytes(p);
        ByteBuf byteBuf = Unpooled.buffer(bytes.length);
        ctx.writeAndFlush(byteBuf);*/
       /* byte[] req = "QUERY TIME ORDER".getBytes();//消息
        ByteBuf firstMessage = Unpooled.buffer(req.length);//发送类
        firstMessage.writeBytes(req);//发送*/
       Person p = new Person();
       p.setName("channelActive");
        ctx.writeAndFlush(p);//flush
    }

    /**
     * 接收到服务器的信息之后调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("调用ClientHandler的channelRead方法开始");
        System.out.println("获取到的服务器的信息为："+msg);
    }

    /**
     * 接收服务器的信息后调用
     * @param channelHandlerContext
     * @param buf
     * @throws Exception
     */
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf buf) throws Exception {
        System.out.println("客户端的messageReceived");
        byte[] bytes = ConvertUtils.byteBufToBytes(buf);
        Person person = (Person) ConvertUtils.bytesToObj(bytes);
        System.out.println("获取到的服务器的信息为："+person.getName());
    }


}
