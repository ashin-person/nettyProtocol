package ljx.ashin.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

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

    }

    /**
     * 初始化客户端配置
     */
    private void initClient(){
        //线程池
        EventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
    }
}
