package ljx.ashin.nt;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Ashin Liang on 2018/1/5.
 */
public class NettyServer {
    private int port;//端口

    public void initServer(int port){
        this.port = port;
        //处理连接的线程池
        NioEventLoopGroup boss = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2);
        //处理业务的线程池
        NioEventLoopGroup workers = new NioEventLoopGroup();
        //启动引导类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,workers);
        //设置socket类型
        bootstrap.channel(NioServerSocketChannel.class);
        //设置配置信息
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);//维持链接的活跃
        bootstrap.option(ChannelOption.SO_BACKLOG,2048);//连接缓冲池的大小
        bootstrap.option(ChannelOption.TCP_NODELAY,true);//不延迟发送
        //设置处理器
        bootstrap.childHandler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new PersonDecoder());//解码器
                channel.pipeline().addLast(new ServerHandler());//处理器
            }
        });

        try {
            ChannelFuture channelFuture = bootstrap.bind(this.port).sync();//绑定端口
            if (channelFuture.isSuccess()){
                System.out.println("服务器启动成功");
            }
            Thread.sleep(30*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NettyServer server = new NettyServer();
        server.initServer(8899);
    }
}
