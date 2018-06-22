package cn.yan.test.spring.boot.database.domain;

public class Message extends MessageKey {
    private Integer messagelength;

    private String testid;

    private String xiaoxi_message;

    public Integer getMessagelength() {
        return messagelength;
    }

    public void setMessagelength(Integer messagelength) {
        this.messagelength = messagelength;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid == null ? null : testid.trim();
    }

    public String getXiaoxi_message() {
        return xiaoxi_message;
    }

    public void setXiaoxi_message(String xiaoxi_message) {
        this.xiaoxi_message = xiaoxi_message == null ? null : xiaoxi_message.trim();
    }
}