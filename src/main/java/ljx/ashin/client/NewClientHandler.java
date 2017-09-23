package ljx.ashin.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ljx.ashin.protocol.SmartCarProtocol;

/**
 *处理器
 * Created by AshinLiang on 2017/9/23.
 */
public class NewClientHandler extends SimpleChannelInboundHandler {

    protected void messageReceived(ChannelHandlerContext channelHandlerContext,
                                   Object o) throws Exception {
        System.out.println("客户端的处理器");
        SmartCarProtocol smartCarProtocol = (SmartCarProtocol)o;
        System.out.println("客户端拿到的信息为:"+new String(smartCarProtocol.getContent()));
    }
}
