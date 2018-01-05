package ljx.ashin.nt;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import ljx.ashin.client.MyClientHandler;
import ljx.ashin.protocol.SmartCarDecoder;
import ljx.ashin.protocol.SmartCarEncoder;

/**
 * Created by Ashin Liang on 2018/1/5.
 */
public class NettyClient {
    private String connecting;//连接窜
    private Integer port;//端口

    public void initClient(String connecting,Integer port){
        this.connecting = connecting;
        this.port = port;
        //工作线程
        NioEventLoopGroup worker = new NioEventLoopGroup();
        //Netty引导类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker);
        //设置通道
        bootstrap.channel(NioSocketChannel.class);
        //设置项
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);//保持活跃
        bootstrap.option(ChannelOption.SO_BACKLOG,2048);//缓存区的大小
        bootstrap.option(ChannelOption.TCP_NODELAY,true);//不延迟发送
        bootstrap.handler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new SmartCarEncoder());
                channel.pipeline().addLast(new SmartCarDecoder());
                channel.pipeline().addLast(new MyClientHandler());
            }
        });

        try {
            ChannelFuture channelFuture = bootstrap.connect(connecting,port).sync();
            if (channelFuture.isSuccess()){
                System.out.println("客户端连接服务器成功");
                Person person = new Person();
                person.setName("jack");
                channelFuture.channel().writeAndFlush(person);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.initClient("127.0.0.1",8899);
    }
}
