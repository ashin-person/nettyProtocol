package ljx.ashin.sendAndRsp;

import java.io.Serializable;

/**
 * Created by Ashin Liang on 2018/1/8.
 */
public class SRObject implements Serializable{
    private String msg;//消息体
    private String code;//响应码

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SRObject{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
