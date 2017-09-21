package ljx.ashin.protocol;

import ljx.ashin.constant.ProtocolConstants;

import java.util.Arrays;

/**
 * 自定义协议的实体类
 * Created by Ashin Liang on 2017/9/21.
 */
public class SmartCarProtocol {
    /**
     * 消息开始的头标志
     */
    private int headerStartFlag = ProtocolConstants.HANDER_START_FLAG;
    /**
     * 消息的长度
     */
    private int length;
    /**
     * 消息内容
     */
    private byte[] content;

    public SmartCarProtocol(int length,byte[] content){
        this.length = length;
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getHeaderStartFlag() {
        return headerStartFlag;
    }

    public void setHeaderStartFlag(int headerStartFlag) {
        this.headerStartFlag = headerStartFlag;
    }

    @Override
    public String toString() {
        return "SmartCarProtocol{" +
                "headerStartFlag=" + headerStartFlag +
                ", length=" + length +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
