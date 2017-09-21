
package ljx.ashin.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import ljx.ashin.protocol.SmartCarDecoder;
import ljx.ashin.protocol.SmartCarEncoder;

/**
 * 服务器端
 * Created by Ashin Liang on 2017/9/21.
 */
public class Server {
    /**
     * 端口
     */
    private int port;

    public Server(int port){
        this.port = port;
        initServer();
    }

    /**
     * 服务器的初始化
     */
    private void initServer(){
        //线程池
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //绑定线程池
        serverBootstrap.group(boss,worker);
        //socket工厂
        serverBootstrap.channel(NioServerSocketChannel.class);
        //设置配置信息
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE,true);//维持链接的活跃
        serverBootstrap.option(ChannelOption.SO_BACKLOG,2048);//连接缓冲池的大小
        serverBootstrap.option(ChannelOption.TCP_NODELAY,true);//不延迟发送

        //设置处理器
        serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new SmartCarDecoder());//解码器
                channel.pipeline().addLast(new SmartCarEncoder());//编码器
                channel.pipeline().addLast(new MyServerHandler());

            }
        });

        try {
           ChannelFuture channelFuture = serverBootstrap.bind(this.port).sync();//绑定端口
            if (channelFuture.isSuccess()){
                System.out.println("服务器启动成功");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
