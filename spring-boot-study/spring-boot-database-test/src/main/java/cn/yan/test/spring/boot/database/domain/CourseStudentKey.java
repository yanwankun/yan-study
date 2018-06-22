package cn.yan.test.spring.boot.database.domain;

public class CourseStudentKey {
    private Integer courId;

    private String stuId;

    public Integer getCourId() {
        return courId;
    }

    public void setCourId(Integer courId) {
        this.courId = courId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }
}