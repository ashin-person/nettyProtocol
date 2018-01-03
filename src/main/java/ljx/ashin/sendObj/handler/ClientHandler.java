package ljx.ashin.sendObj.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by AshinLiang on 2018/1/3.
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf buf) throws Exception {
        System.out.println("调用ClientHandler开始");
        //1、
    }
}
