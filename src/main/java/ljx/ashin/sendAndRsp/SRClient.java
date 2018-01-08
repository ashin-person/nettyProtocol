package ljx.ashin.sendAndRsp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Ashin Liang on 2018/1/8.
 */
public class SRClient {

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
                channel.pipeline().addLast(new SREncoder());//处理发送出去的数据，将发送出去的数据编码
                channel.pipeline().addLast(new SRDecoder());//数据解码
                channel.pipeline().addLast(new SREncoderClientHandler());//处理得到的数据
            }
        });

        try{
            //连接服务器
            ChannelFuture channelFuture = bootstrap.connect(connecting,port).sync();
            if (channelFuture.isSuccess()){
                System.out.println("客户端连接服务器成功");
            }
            SRObject srObject = new SRObject();
            srObject.setMsg("发送正式信息");
            channelFuture.channel().writeAndFlush(srObject);
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SRClient client = new SRClient();
        client.initClient("127.0.0.1",8898);
    }
}
