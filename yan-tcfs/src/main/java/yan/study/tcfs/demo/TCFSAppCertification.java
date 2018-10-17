package yan.study.tcfs.demo;

/**
 * Created 开发者签发的证书
 * User  wankunYan
 * Date  2018/8/21
 * Time  14:25
 */
public class TCFSAppCertification {
    private String appid; // 开发者标识
    private long   timestamp; // sign的过期时间点（秒）
    private String sign;  // 对开发者身份的校验值

    public TCFSAppCertification(String appid, long timestamp, String sign) {
        this.appid = appid;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
