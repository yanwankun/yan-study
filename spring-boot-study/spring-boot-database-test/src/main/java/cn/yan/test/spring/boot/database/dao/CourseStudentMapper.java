package cn.yan.test.spring.boot.database.dao;

import cn.yan.test.spring.boot.database.domain.CourseStudent;
import cn.yan.test.spring.boot.database.domain.CourseStudentKey;

public interface CourseStudentMapper {
    int deleteByPrimaryKey(CourseStudentKey key);

    int insert(CourseStudent record);

    int insertSelective(CourseStudent record);

    CourseStudent selectByPrimaryKey(CourseStudentKey key);

    int updateByPrimaryKeySelective(CourseStudent record);

    int updateByPrimaryKeyWithBLOBs(CourseStudent record);

    int updateByPrimaryKey(CourseStudent record);
}