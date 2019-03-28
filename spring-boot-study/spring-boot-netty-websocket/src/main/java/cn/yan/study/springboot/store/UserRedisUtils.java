package cn.yan.study.springboot.store;

import cn.yan.study.springboot.domain.User;
import cn.yan.study.springboot.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

/**
 * Created by gentlemen_yan on 2019/3/23.
 */
public class UserRedisUtils {
    private static String prefix = "yan.spring.boot.netty.chat.";
    private static String exist_count = "exist.count";

    private static String getCacheId(String key) {
        return prefix + key;
    }

    public static void saveAccountInfo(User user) {
        String cacheId = getCacheId(user.getLoginName());
        RedisUtils.set(cacheId, JSON.toJSONString(user));
    }

    public static boolean isExist(String loginName) {
        String cacheId = getCacheId(loginName);
        return null != RedisUtils.get(cacheId);
    }

    public static long getExistUserCount() {
        String cacheId = getCacheId(exist_count);
        String cache = RedisUtils.get(cacheId);
        if (cache == null) {
            return 1L;
        } else {
            return Long.valueOf(cache);
        }
    }

    public static User getExistUser(String loginName) {
        String cacheId = getCacheId(loginName);
        String cache = RedisUtils.get(cacheId);
        if (null == cache) {
            return null;
        }
        return JSON.parseObject(cache, User.class);
    }
}
