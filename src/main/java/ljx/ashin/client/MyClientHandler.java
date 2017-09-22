package ljx.ashin.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ljx.ashin.protocol.SmartCarProtocol;

/**
 * 客户端的处理器
 * Created by Ashin Liang on 2017/9/21.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<SmartCarProtocol> {
    protected void messageReceived(ChannelHandlerContext channelHandlerContext,
                                   SmartCarProtocol smartCarProtocol) throws Exception {
        byte[] content = smartCarProtocol.getContent();
        String msg = new String(content);
        System.out.println("获取到的服务器的信息为："+msg);

    }
}
