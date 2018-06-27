package cn.yan.study.es.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/27
 * Time  16:38
 */
public class Student {
    /**
     * 学生姓名
     */
    private String stu_name;
    /**
     * 学生生日
     */
    private String stu_birthday;
    /**
     * 性别
     */
    private String stu_sex;

    public Student() {
    }

    public Student(String stu_name, String stu_birthday, String stu_sex) {
        this.stu_name = stu_name;
        this.stu_birthday = stu_birthday;
        this.stu_sex = stu_sex;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_birthday() {
        return stu_birthday;
    }

    public void setStu_birthday(String stu_birthday) {
        this.stu_birthday = stu_birthday;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public Map<String, Object> getMapObj() {
        Map<String, Object> objMap = new HashMap<>();
        objMap.put("stu_name", this.getStu_name());
        objMap.put("stu_birthday", this.getStu_birthday());
        objMap.put("stu_sex", this.getStu_sex());
        return objMap;
    }
    public String getDocId() {
        return "elastic_student_" + this.hashCode() + "_" + this.getStu_name();
    }
}
