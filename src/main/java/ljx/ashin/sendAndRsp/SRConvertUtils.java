package ljx.ashin.sendAndRsp;

import io.netty.buffer.ByteBuf;

import java.io.*;

/**
 * Created by Ashin Liang on 2018/1/8.
 */
public class SRConvertUtils {
    /**
     * 将ByteBuf转换为byte[]
     * @param byteBuf
     * @return
     */
    public static byte[] byteBufToBytes(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        return bytes;
    }
    /**
     * 对象转化为字节数组
     * @param object
     * @return
     */
    public static byte[] ObjToBytes(Object object){
        byte[] resultBytes = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            resultBytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=byteArrayOutputStream){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=objectOutputStream){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultBytes;
    }

    /**
     *将字节数组转化为对象
     * @param bytes
     * @return
     */
    public static Object bytesToObj(byte[] bytes){
        Object obj = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try{
            if (bytes == null){
                byteArrayInputStream = new ByteArrayInputStream(bytes);
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                obj = objectInputStream.readObject();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (byteArrayInputStream!=null){
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
