package ljx.ashin.sendObj.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import ljx.ashin.sendObj.coder.ObjEncoder;

/**
 * Created by AshinLiang on 2018/1/3.
 */
public class NettyClient {

    private  String connecting;//连接串
    private int port;//端口号

    public void initClient(String connecting,int port){
        this.connecting = connecting;
        this.port = port;

        NioEventLoopGroup boss = new NioEventLoopGroup();//线程池，这个线程池用来处理连接、接受数据、发送数据
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(boss);
        bootstrap.channel(NioSocketChannel.class);//通道类型
        //设置项
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);//保持活跃
        bootstrap.option(ChannelOption.SO_BACKLOG,2048);//缓存区的大小
        bootstrap.option(ChannelOption.TCP_NODELAY,true);//不延迟发送
        bootstrap.handler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new ObjEncoder());
            }
        });
    }
}
