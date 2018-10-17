package yan.study.tcfs.demo;

/**
 * Created 用户签发的证书
 * User  wankunYan
 * Date  2018/8/21
 * Time  14:27
 */
public class TCFSUserCertification {
    private String address; // 用户身份标识
    long timestamp; // 签名的过期时间点（秒）
    private String nonce; // 随机数
    int data_len; // 扩展数据长度
    private String data; // 扩展数据
    private String sign; // 对结构体中成员的签名，用于对用户身份进行自校验

    public TCFSUserCertification(String address, long timestamp, String nonce, int data_len, String data, String sign) {
        this.address = address;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.data_len = data_len;
        this.data = data;
        this.sign = sign;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public int getData_len() {
        return data_len;
    }

    public void setData_len(int data_len) {
        this.data_len = data_len;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
