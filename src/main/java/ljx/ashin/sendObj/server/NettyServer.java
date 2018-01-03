package ljx.ashin.sendObj.server;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import ljx.ashin.sendObj.coder.ObjDecoder;
import ljx.ashin.sendObj.coder.ObjEncoder;
import ljx.ashin.sendObj.handler.ServerHandler;

import java.nio.channels.SocketChannel;

/**
 * netty的服务端
 * Created by AshinLiang on 2018/1/2.
 */
public class NettyServer {

    private Integer port;//端口号

    /**
     * 初始化服务器端
     * @param port
     */
    public void initServer(Integer port){
        this.port = port;
        //处理连接的线程池
        NioEventLoopGroup boss = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2);
        //处理业务的线程池
        NioEventLoopGroup worker = new NioEventLoopGroup();
        //启动引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //绑定线程池
        serverBootstrap.group(boss,worker);
        //通道类型
        serverBootstrap.channel(NioServerSocketChannel.class);
        //设置配置信息
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE,true);//维持链接的活跃
        serverBootstrap.option(ChannelOption.SO_BACKLOG,2048);//连接缓冲池的大小
        serverBootstrap.option(ChannelOption.TCP_NODELAY,true);//不延迟发送

        //设置处理器
        serverBootstrap.childHandler(new ChannelInitializer<Channel>(){
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new ObjDecoder());
                channel.pipeline().addLast(new ServerHandler());
            }
        });

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();//绑定端口
            if (channelFuture.isSuccess()){
                System.out.println("服务器启动成功");
            }
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
