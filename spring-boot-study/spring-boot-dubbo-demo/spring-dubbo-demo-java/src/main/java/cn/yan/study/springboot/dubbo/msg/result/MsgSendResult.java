package cn.yan.study.springboot.dubbo.msg.result;
/**
 * Created by gentlemen_yan on 2019/3/3.
 */
public class MsgSendResult {
    private String msg;
    private Boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
