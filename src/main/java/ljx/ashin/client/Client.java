package ljx.ashin.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import ljx.ashin.protocol.SmartCarDecoder;
import ljx.ashin.protocol.SmartCarEncoder;
import ljx.ashin.protocol.SmartCarProtocol;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Created by Ashin Liang on 2017/9/21.
 */
public class Client {
    /**
     * 端口
     */
    private int port;
    /**
     * ip地址
     */
    private String ip;

    public Client(int port,String ip){
        this.port = port;
        this.ip = ip;
        initClient();
    }

    /**
     * 初始化客户端配置
     */
    private void initClient(){
        //线程池
        EventLoopGroup worker = new NioEventLoopGroup();

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
            //连接服务器
            ChannelFuture channelFuture = bootstrap.connect(ip,port).sync();
            if (channelFuture.isSuccess()){
                System.out.println("客户端连接服务器成功");
                BufferedReader bufferedReader = null;
                while (true){
                    System.out.println("请输入要发送的信息");
                    InputStreamReader isr = new InputStreamReader(System.in);
                    bufferedReader = new BufferedReader(isr);
                    String msg = bufferedReader.readLine();
                    SmartCarProtocol smartCarProtocol = new SmartCarProtocol(msg.getBytes().length,
                            msg.getBytes());
                    channelFuture.channel().writeAndFlush(smartCarProtocol);
                }
                /*String clientMsg = "客户端的请求信息";
                SmartCarProtocol smartCarProtocol = new SmartCarProtocol(clientMsg.getBytes().length,clientMsg.getBytes());
                channelFuture.channel().writeAndFlush(smartCarProtocol);*/

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client(8765,"127.0.0.1");
    }
}
