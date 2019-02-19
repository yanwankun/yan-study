package cn.yan.study.utils;

import cn.yan.study.utils.cache.ConcurrentHashMapCacheUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class BloomUtils {
    private static final int NUM_SLOTS = 1024*8;
    private static final int NUM_HASH = 8;
    private List<BloomStatus> statusList;
    private final String cache_key = "bloom.cache.id";
    private final List<String> msgList =
            Lists.newArrayList(
                    "hello"
                    , "HowAreYou"
                    , "welcome"
                    , "please"
                    , "test"
                    , "add"
                    , "sub"
                    , "flower"
                    , "gentlemen"
            );

    public BloomUtils() {
        init();
    }

    /**
     *  先从缓存取数据 如果没有就创建
     */
    public void init() {
        getStatus();
        if (statusList == null || statusList.size() == 0) {
            System.out.println("构建状态");
            statusList = Lists.newArrayListWithCapacity(NUM_HASH);
            if (msgList.size() < NUM_HASH) {
                throw new RuntimeException("初始单词个数不足");
            }
            for (int i = 0; i < NUM_HASH ; i++) {
                statusList.add(new BloomStatus(msgList.get(i), new BigInteger("0")));
            }
        }
    }

    public void showStats() {
        System.out.println(JSON.toJSONString(statusList));
    }

    public void getStatus() {
        statusList = JSON.parseArray((String) ConcurrentHashMapCacheUtils.getCache(cache_key), BloomStatus.class);
    }

    public void save() {
        String desc = JSON.toJSONString(statusList);
        ConcurrentHashMapCacheUtils.setCache(cache_key, desc);
    }

    public static void main(String[] args) {
        //测试代码
        BloomUtils bf = new BloomUtils();
        System.out.println("ttt");
        ArrayList<String> contents = new ArrayList<>();
        contents.add("sldkjelsjf");
        contents.add("ggl;ker;gekr");
        contents.add("wieoneomfwe");
        contents.add("sldkjelsvrnlkjf");
        contents.add("ksldkflefwefwefe");

        for (String temp : contents){
            bf.addElement(temp, true);
        }

        bf.addElement(contents.get(0), false);

        System.out.println(bf.check("sldkjelsvrnlkjf"));
        System.out.println(bf.check("sldkjelsvrnkjf"));
        bf.init();
        bf.showStats();
    }

    private int getHash(final Object message,int index){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            String str  = message + statusList.get(index).getMessage();
            byte[] bytes = str.getBytes();
            md5.update(bytes);
            BigInteger bi = new BigInteger(md5.digest());

            return Math.abs(bi.intValue())%NUM_SLOTS;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BloomUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void addElement(String message, boolean more){
        for(int i = 0; i < NUM_HASH; i++){
            int hashcode = getHash(message,i);
            if(!statusList.get(i).getStats().testBit(hashcode)){
                statusList.get(i).setStats(statusList.get(i).getStats().or(new BigInteger("1").shiftLeft(hashcode)));
            }
        }
        if (!more) {
            save();
        }

    }

    public boolean check(Object message){
        for(int i = 0; i < NUM_HASH; i++){
            int hashcode = getHash(message,i);
            if(!statusList.get(i).getStats().testBit(hashcode)){
                return false;
            }
        }
        return true;
    }
}
