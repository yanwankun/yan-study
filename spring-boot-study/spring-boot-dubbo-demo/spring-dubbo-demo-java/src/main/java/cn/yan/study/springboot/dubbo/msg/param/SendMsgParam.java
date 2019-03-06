package cn.yan.study.springboot.dubbo.msg.param;

import java.util.Date;

/**
 * Created by gentlemen_yan on 2019/3/3.
 */
public class SendMsgParam {
    private String senderName;
    private Date sendTime;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
