package cn.yan.study.springboot.websocket.utils;

import java.util.Random;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class YanStringUtils {

    public static String getRandomStr(int length) {
        return "aaa" + new Random().nextLong();
    }
}
