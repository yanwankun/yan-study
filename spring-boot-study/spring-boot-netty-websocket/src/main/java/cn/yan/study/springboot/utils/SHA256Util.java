package cn.yan.study.springboot.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gentlemen_yan on 2019/3/23.
 */
public class SHA256Util {

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
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
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
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String test = getSHA256StrJava("this is a test");
        System.out.println(test);
    }

}
