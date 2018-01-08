package ljx.ashin.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ljx.ashin.protocol.SmartCarProtocol;

/**
 * 服务器的处理器
 * Created by Ashin Liang on 2017/9/21.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<SmartCarProtocol> {
    protected void messageReceived(ChannelHandlerContext channelHandlerContext,
                                   SmartCarProtocol smartCarProtocol) throws Exception {
        byte[] body = smartCarProtocol.getContent();
        Long startTime = System.currentTimeMillis();
        System.out.println("得到客户端的请求信息为:"+new String(body));
        String msg = "服务器端已经接受到您的请求，正在处理中，请稍后";
        SmartCarProtocol msgScp = new SmartCarProtocol(msg.getBytes().length,msg.getBytes());
        channelHandlerContext.write(msgScp);
        Long endTime = System.currentTimeMillis();
        channelHandlerContext.flush();

    }
}
