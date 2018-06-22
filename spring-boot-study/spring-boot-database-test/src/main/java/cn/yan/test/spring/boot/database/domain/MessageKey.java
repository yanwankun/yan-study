package cn.yan.test.spring.boot.database.domain;

import java.util.Date;

public class MessageKey {
    private String userid;

    private Date sendtime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }
}