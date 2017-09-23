package ljx.ashin.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ljx.ashin.protocol.SmartCarProtocol;

/**
 * Created by AshinLiang on 2017/9/23.
 */
public class NewServerHandler extends SimpleChannelInboundHandler {
    protected void messageReceived(ChannelHandlerContext channelHandlerContext,
                                   Object o) throws Exception {
        System.out.println("服务器端的处理器");
        SmartCarProtocol smartCarProtocol = (SmartCarProtocol)o;
        String msg = new String(smartCarProtocol.getContent());
        System.out.println("服务器端接收到客户端的信息为:"+msg);

        String rspMsg = "服务器已经接受到客户端的信息，正在处理中，请稍后";
        SmartCarProtocol rsp = new SmartCarProtocol(rspMsg.getBytes().length,rspMsg.getBytes());
        channelHandlerContext.writeAndFlush(rsp);

    }
}
