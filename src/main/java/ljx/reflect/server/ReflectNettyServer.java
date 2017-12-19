package ljx.reflect.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 自己新写的netty的服务器端
 * Created by Ashin Liang on 2017/12/19.
 */
public class ReflectNettyServer {

    private int port;//端口号

    public ReflectNettyServer(int port){
        this.port = port;
        init(this.port);
    }

    /**
     * 初始化方法
     * @param port
     */
    public void init(int port){
        //线程池
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        //启动引导类,来引导和启动服务
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ///指定通道类型为NioServerSocketChannel，设置InetSocketAddress让服务器监听某个端口已等待客户端连接。
        serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).
                localAddress("127.0.0.1",port).childHandler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new ReflectServerHandler());
            }
        });
        try {
            //最后绑定服务器等待直到绑定完成，调用sync()方法会阻塞直到服务
            // 器完成绑定,然后服务器等待通道关闭，因为使用sync()，所以关闭操作也会被阻塞。
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("开始监听，端口号为："+channelFuture.channel().localAddress());
            channelFuture.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
