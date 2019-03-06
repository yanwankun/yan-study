package cn.yan.study.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */
public class JedisApi {

    private static final Logger LOG = LoggerFactory.getLogger(JedisApi.class);

    private volatile static JedisApi jedisApi;

    /**
     * 保存多个连接源
     */
    private static Map<String, JedisPool> poolMap = new HashMap<String, JedisPool>();

    private JedisApi() {
    }

    /**
     * @Description: jedisPool 池
     * @Param: [ip, port]
     * @return: redis.clients.jedis.JedisPool
     * @Author: imdalai
     * @Date: 2018/1/15
     */
    private static JedisPool getPool(String ip, int port) {

        try {
            String key = ip + ":" + port;
            JedisPool pool = null;
            if (!poolMap.containsKey(key)) {
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxIdle(RedisConfig.MAX_IDLE);
                config.setMaxTotal(RedisConfig.MAX_ACTIVE);
                //  在获取连接的时候检查有效性, 默认false
                config.setTestOnBorrow(true);
                //  在空闲时检查有效性, 默认false
                config.setTestOnReturn(true);
                pool = new JedisPool(config, ip, port, RedisConfig.TIME_OUT);
                poolMap.put(key, pool);
            } else {
                pool = poolMap.get(key);
            }
            return pool;
        } catch (Exception e) {
            LOG.error("init jedis pool failed ! " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * @Description: 线程安全单列模式
     * @Param: []
     * @return: JedisApi
     * @Author: imdalai
     * @Date: 2018/1/15
     */
    public static JedisApi getRedisApi() {

        if (jedisApi == null) {
            synchronized (JedisApi.class) {
                if (jedisApi == null) {
                    jedisApi = new JedisApi();
                }
            }
        }
        return jedisApi;
    }

    /**
     * @Description: 获取一个jedis连接
     * @Param: [ip, port]
     * @return: redis.clients.jedis.Jedis
     * @Author: imdalai
     * @Date: 2018/1/15
     */
    public Jedis getRedis(String ip, int port, String pwd) {
        Jedis jedis = null;
        int count = 0;
        while (jedis == null && count <= RedisConfig.RETRY_NUM) {
            try {
                jedis = getPool(ip, port).getResource();
                jedis.auth(pwd);
            } catch (Exception e) {
                LOG.error("get redis failed ! " + e.getMessage(), e);
                count++;
            }
        }
        return jedis;
    }

    /**
     * @Description: 释放jedis到jedisPool中
     * @Param: [jedis, ip, port]
     * @return: void
     * @Author: imdalai
     * @Date: 2018/1/15
     */
    public void closeRedis(Jedis jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                LOG.error("colse jedis failed ! " + e.getMessage(), e);
            }
        }
    }
}
