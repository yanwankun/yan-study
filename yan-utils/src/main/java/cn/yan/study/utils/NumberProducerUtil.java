package cn.yan.study.utils;

import org.apache.commons.collections.set.SynchronizedSet;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created
 * User  wankunYan
 * Date  2018/9/26
 * Time  11:36
 */
public class NumberProducerUtil {
    /**
     * 前缀 6
     */
    private static String prefix;
    /**
     * 每一秒最大数目 4
     */
    private static Integer max;
    /**
     * 生产者编号 4
     */
    private static String proStr;
    /**
     * 自定义编号 4
     */
    private static String diyStr;

    /** 毫秒内序列(0~max) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;



    public NumberProducerUtil(String prefix, Integer max, String proStr, String diyStr) {
        this.prefix = prefix;
        this.max = max;
        this.proStr = proStr;
        this.diyStr = diyStr;
    }

    public synchronized String nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = sequence + 1;
            //毫秒内序列溢出
            if (sequence > max) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        StringBuilder sb = new StringBuilder(NumberProducerUtil.prefix);
        sb.append(proStr).append(diyStr);
        sb.append(YanDateUtils.getDateString(new Date(lastTimestamp), YanDateUtils.TIME_STRING_4));
        sb.append(leftAppend(4, sequence + ""));

        return sb.toString();
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        sequence = 0;
        return timestamp;
    }

    public static String leftAppend(int length, String value) {
        if (value.length() > length) {
            return value.substring(0, length);
        }
        while (value.length() < length) {
            value = "0" + value;
        }
        return value;
    }

    public static void main(String[] args) {
        NumberProducerUtil idWorker = new NumberProducerUtil("testab", 9999, "proA","diyT");
        for (int i=0; i<1000;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j =0; j<1000; j++) {
                        String id = idWorker.nextId();
                        System.out.println(id);
                    }
                }
            }).start();
        }
    }
}

