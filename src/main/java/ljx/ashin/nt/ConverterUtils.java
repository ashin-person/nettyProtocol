package ljx.ashin.nt;

import io.netty.buffer.ByteBuf;

import java.io.*;

/**
 * 转换工具类
 * Created by Ashin Liang on 2018/1/5.
 */
public class ConverterUtils {
    /**
     * 将byte[]转换为Object
     * @param bytes
     * @return
     */
    public static Object bytesToObject(byte[] bytes){
        Object object = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭流
            if (null!=objectInputStream){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=byteArrayInputStream){
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    /**
     * 将Object对象转换为byte[]数组,用ObjectOoutputStream包装字节流
     * @param object
     * @return
     */
    public static byte[] ObjToBytes(Object object){
        //对象流
        ObjectOutputStream objectOutputStream = null;
        //二进制字节流
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bytes = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            bytes = byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭流
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream!=null){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    /**
     * 将byteBuf转换为byte[]
     * @param byteBuf
     * @return
     */
    public static byte[] byteBufToBytes(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        return bytes;
    }
}
