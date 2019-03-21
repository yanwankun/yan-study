package cn.yan.study.springboot.websocket.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class YanStringUtils {

    private static String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getRandomStr(int length) {
        return RandomStringUtils.random(length, source);
    }
}
