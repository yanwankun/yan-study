package cn.yan.study.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gentlemen_yan on 2019/3/30.
 */
public class Sha256Utils {
    /**
     　　* 利用java原生的摘要实现SHA256加密
     　　* @param str 加密后的报文
     　　* @return
     　　*/
    public static String getSHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
　　* 将byte转为16进制
　　* @param bytes
　　* @return
　　*/
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static String source = "76752b817e39a7e7e7b2c3cd30ce4f5600ff6261b336dd8beaea012c1a6bc208";
    public static String dest = "39c62369d1466174995d08cb1bd37b2e066302510a77ee3a6d3d63752b59411e";

    public static void main(String[] args) {
        String test = "76752b817e39a7e7e7b2c3cd30ce4f5600ff6261b336dd8beaea012c1a6bc208";
        System.out.println(test.length());
        System.out.println(test.hashCode());
        System.out.println(getSHA256StrJava(test));

        System.out.println(1.5 * 1024 * 0.047);
    }
}
