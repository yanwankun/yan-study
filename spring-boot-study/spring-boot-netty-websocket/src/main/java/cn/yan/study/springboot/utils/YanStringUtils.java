package cn.yan.study.springboot.utils;

import org.apache.commons.lang3.RandomStringUtils;

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
