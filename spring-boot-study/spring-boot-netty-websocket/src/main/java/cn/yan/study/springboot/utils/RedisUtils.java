package cn.yan.study.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by gentlemen_yan on 2019/3/23.
 */
@Component
@Slf4j
public class RedisUtils {

    private static StringRedisTemplate redisTemplate;

    private static final long DEFAULT_TIME = 3600;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * 缓存value操作
     *
     * @param k key
     * @param v value
     */
    public static void set(String k, String v) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(k, v);
            redisTemplate.expire(k, DEFAULT_TIME, TimeUnit.SECONDS);
        } catch (Throwable t) {
            log.error("redis set key wrong ", t);
        }
    }

    /**
     * 缓存value操作
     *
     * @param k    key
     * @param v    value
     * @param time time
     */
    public static void set(String k, String v, long time) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            } else {
                throw new RuntimeException("redis set time unable");
            }
        } catch (Throwable t) {
            log.error("redis set key wrong ", t);
        }
    }

    public static boolean containsKey(String k) {
        try {
            return redisTemplate.hasKey(k);
        } catch (Throwable t) {
            log.error("redis contain key wrong", t);
        }
        return false;
    }

    /**
     * 获取缓存
     *
     * @param k key
     * @return String
     */
    public static String get(String k) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(k);
        } catch (Throwable t) {
            log.error("redis get key wrong", t);
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param k key
     * @return true
     */
    public static boolean delete(String k) {
        try {
            redisTemplate.delete(k);
            return true;
        } catch (Throwable t) {
            log.error("redis remove key wrong", t);
        }
        return false;
    }

    /**
     * delete
     *
     * @param k     key
     * @param value value
     * @return boolean
     */
    public static boolean delete(String k, Long value) {
        try {
            ZSetOperations zSetOps = redisTemplate.opsForZSet();
            zSetOps.removeRangeByScore(k, value, value);
            return true;
        } catch (Throwable t) {
            log.error("redis get key wrong ", t);
        }
        return false;
    }

    /**
     * getZSet
     *
     * @param k key
     * @return Set
     */
    public static Set getZSet(String k) {
        try {
            ZSetOperations zsetOps = redisTemplate.opsForZSet();
            return zsetOps.range(k, 0, -1);
        } catch (Throwable t) {
            log.error("redis get key wrong ", t);
        }
        return null;
    }

    /**
     * deleteZSet
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    public static boolean deleteZSet(String k, String v) {
        try {
            ZSetOperations zsetOps = redisTemplate.opsForZSet();
            zsetOps.remove(k, v);
            return true;
        } catch (Throwable t) {
            log.error("redis get key wrong ", t);
        }
        return false;
    }

    /**
     * setList
     *
     * @param k    key
     * @param list list
     * @param time time
     */
    public static void setList(String k, List list, Long time) {
        try {
            ListOperations listOps = redisTemplate.opsForList();
            Long size = listOps.size(k);
            for (int i = 0; i < size; i++) {
                listOps.leftPop(k);
            }
            listOps.rightPushAll(k, list);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
        } catch (Throwable t) {
            log.error("redis list set key wrong", t);
        }
    }

    public static Long getExpire(String key) {
        Long time = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return time;
    }
}
