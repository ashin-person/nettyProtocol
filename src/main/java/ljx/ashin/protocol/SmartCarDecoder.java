package ljx.ashin.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import ljx.ashin.constant.ProtocolConstants;

import java.util.List;

/**
 * smartCar协议的解码器
 * Created by Ashin Liang on 2017/9/21.
 */
public class SmartCarDecoder extends ByteToMessageDecoder {
    /**
     * 基本长度=协议开始的标志(一个int类型4个字节)+数据的长度(一个int类型4个字节)
     */
    public final int  BASE_LENGTH= 4+4;

    /**
     * 该方法中，如果return null的话，则代表继续等待数据发送过来
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        //可读长度必须要大于基本长度
        if (byteBuf.readableBytes()>=BASE_LENGTH){

            //防止socket字节流攻击
            //防止客户端传过来的数据过大
            //因为，太大的数据是不合理的
            if (byteBuf.readableBytes()>2048){
                //跳过这些数据
                byteBuf.skipBytes(byteBuf.readableBytes());
            }
            //记录报头开始的位置
            int beginReader;
            while (true){
                //获取报头开始的index
                beginReader = byteBuf.readerIndex();
                //标记包头开始的index
                byteBuf.markReaderIndex();
                if (byteBuf.readInt()== ProtocolConstants.HANDER_START_FLAG){//找到了报文的开头标志
                    break;
                }
                //没有找到开头标志，重置位置，并读取下一个
                byteBuf.resetReaderIndex();
                byteBuf.readByte();
                //再次检查数据的长度是否满足要求
                if (byteBuf.readableBytes()<BASE_LENGTH){
                    return;
                }
            }
            //获取消息的长度
            int length = byteBuf.readInt();
            //判断请求数据包的数据是否到齐
            if (byteBuf.readableBytes()<length){
                //还原读指针
                byteBuf.readerIndex(beginReader);
                return;
            }
            //读取数据包的正文数据
            byte[] content = new byte[length];
            byteBuf.readBytes(content);

            SmartCarProtocol smartCarProtocol = new SmartCarProtocol(length,content);
            list.add(smartCarProtocol);
        }

    }
}
