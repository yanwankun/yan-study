package cn.yan.study.utils;

import java.math.BigInteger;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class BloomStatus {
    /**
     * hash 前缀
     */
    private String message;

    /**
     * hash 分布状态
     */
    private BigInteger stats = new BigInteger("0");

    public BloomStatus(String message, BigInteger stats) {
        this.message = message;
        this.stats = stats;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigInteger getStats() {
        return stats;
    }

    public void setStats(BigInteger stats) {
        this.stats = stats;
    }
}
