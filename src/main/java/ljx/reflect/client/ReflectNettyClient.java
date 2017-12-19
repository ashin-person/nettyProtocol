package ljx.reflect.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *自己新写的netty的客户端
 * Created by Ashin Liang on 2017/12/19.
 */
public class ReflectNettyClient {
    private String connectString;
    private int port;

    public ReflectNettyClient(String connectString,int port){
        this.connectString = connectString;
        this.port = port;
    }

    public void init(){
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();//线程池

        Bootstrap bootstrap =new Bootstrap();
        bootstrap.group(eventLoopGroup).localAddress(connectString,port).channel(NioServerSocketChannel.class).
                handler(new ChannelInitializer<SocketChannel>() {
                    //添加一个ChannelHandler，客户端成功连接服务器后就会被执行
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ReflectClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
