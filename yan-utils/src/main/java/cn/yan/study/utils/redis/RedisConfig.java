package cn.yan.study.utils.redis;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */
public class RedisConfig {
    public static final int MAX_IDLE = 50;
    public static final int MAX_ACTIVE = 100;
    public static final int TIME_OUT = 60 * 1000;
    public static final int RETRY_NUM = 3;
}
