package cn.yan.study.es.test;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/27
 * Time  17:42
 */
public class School {
    /**
     * 学校名称
     */
    private String school_name;
    /**
     * 入学年份
     */
    private String start_year;
    /**
     * 毕业年份
     */
    private String end_year;
    /**
     * 学校地址
     */
    private String school_address;

    public School(String school_name, String start_year, String end_year, String school_address) {
        this.school_name = school_name;
        this.start_year = start_year;
        this.end_year = end_year;
        this.school_address = school_address;
    }

    public School() {
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getStart_year() {
        return start_year;
    }

    public void setStart_year(String start_year) {
        this.start_year = start_year;
    }

    public String getEnd_year() {
        return end_year;
    }

    public void setEnd_year(String end_year) {
        this.end_year = end_year;
    }

    public String getSchool_address() {
        return school_address;
    }

    public void setSchool_address(String school_address) {
        this.school_address = school_address;
    }

    public String getDocId() {
        return "elastic_student_school_" + this.hashCode() + "_" + this.getSchool_name();
    }
}
