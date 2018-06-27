package cn.yan.study.es.test;

import cn.yan.study.es.client.EsClient;
import cn.yan.study.es.utils.EsAdminClientUtils;
import cn.yan.study.es.utils.EsClientUtils;
import cn.yan.study.utils.ObjectUtils;
import cn.yan.study.utils.YanDateUtils;
import jdk.nashorn.internal.runtime.JSONFunctions;
import net.minidev.json.JSONObject;
import org.apache.http.client.utils.DateUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.HasParentQueryBuilder;
import org.elasticsearch.join.query.ParentIdQueryBuilder;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/22
 * Time  11:33
 */
public class EsParentChildTest {

    private static TransportClient client = EsClient.getTransportClient();
    /**
     * 这个是为了测试es 的父子关系
     */

    private static Student student1 = new Student("严万坤", "1994-06-07", "man");
    private static Student student2 = new Student("严万囍","1994-06-07", "man");
    private static Student student3 = new Student("何**","1992-07-23", "women");
    private static Student student4 = new Student("严*","1994-01-17", "women");
    private static Student student5 = new Student("冯*","1993-12-15", "women");


    public static void main(String[] args) {
//        addStudent();
//        addSchool();
        search();
    }

    public static void addStudent()  {
        List<Student> studentList = Arrays.asList(student1,student2,student3,student4,student5);
        for (Student student : studentList) {
            EsClientUtils.addDocument("yan_index_student", "student", student.getDocId(), null, student.getMapObj());
        }
    }

    public static void addSchool() {
        School school1 = new School("阆中中学", "2010-07-05", "2013-06-09", "四川省阆中市古城区");
        School school2 = new School("木兰乡信善小学", "2006-09-01", "2010-06-14", "四川省阆中市木兰乡");
        School school3 = new School("四川师范大学", "2013-09-05", "2017-07-09", "四川省成都市龙泉驿");
        School school4 = new School("阆中师范学校", "2009-07-05", "2012-06-09", "四川省阆中市");

        Map<Student, List<School>> studentSchoolListMap = new HashMap<>();
        List<School> schoolList = new ArrayList<>();
        schoolList.add(school1);
        schoolList.add(school2);
        schoolList.add(school3);

        studentSchoolListMap.put(student1, schoolList);
        schoolList = new ArrayList<>();
        schoolList.add(school2);
        schoolList.add(school4);
        studentSchoolListMap.put(student3, schoolList);

        for (Map.Entry<Student, List<School>> entry : studentSchoolListMap.entrySet()) {
            for (School school : entry.getValue()) {
                EsClientUtils.addDocument("yan_index_student_school", "stu_school", school.getDocId(), entry.getKey().getDocId(), ObjectUtils.getObjMap(school));
            }
        }
    }

    public static void search() {
//        ParentIdQueryBuilder parentIdQueryBuilder = new ParentIdQueryBuilder("student", )
//        HasParentQueryBuilder hpqb=QueryBuilders.hasParentQuery("branch",QueryBuilders.idsQuery().ids("london"));
        SearchResponse response = client.prepareSearch("index1", "index2")

                .setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(ParentIdQueryBuilder.parseInnerQueryBuilder(XContentParser))                 // Query
                .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();
    }
}
