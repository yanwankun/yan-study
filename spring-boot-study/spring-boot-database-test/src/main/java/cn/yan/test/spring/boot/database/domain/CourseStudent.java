package cn.yan.test.spring.boot.database.domain;

public class CourseStudent extends CourseStudentKey {
    private String pinUId;

    private String pingJia;

    public String getPinUId() {
        return pinUId;
    }

    public void setPinUId(String pinUId) {
        this.pinUId = pinUId == null ? null : pinUId.trim();
    }

    public String getPingJia() {
        return pingJia;
    }

    public void setPingJia(String pingJia) {
        this.pingJia = pingJia == null ? null : pingJia.trim();
    }
}