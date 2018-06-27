package cn.yan.study.es.utils;

import cn.yan.study.es.client.EsClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/26
 * Time  14:58
 */
public class EsAdminClientUtils {


    private static IndicesAdminClient adminClient = EsClient.getTransportClient().admin().indices();

    /**
     * 创建索引
     * @throws IOException
     */
    public static void CreateIndex(String indexName, Integer shardNumber, Integer replicaNumber, String type) throws IOException {

        Settings settings = Settings.builder().put("index.number_of_shards", shardNumber).put("index.number_of_replicas", replicaNumber).build();
        CreateIndexRequestBuilder cib = adminClient.prepareCreate(indexName).setSettings(settings);

//        { 姓名，出生日期，身高，学校，公司,居住地址}
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()

                .startObject("properties") //设置之定义字段

                // 姓名
                .startObject("name")
                .field("type","keyword") //设置数据类型
                .endObject()

                // 出生日期
                .startObject("birthday")
                .field("type","date")  //设置Date类型
                .field("format","yyyy-MM-dd HH:mm:ss") //设置Date的格式
                .endObject()

                // 身高
                .startObject("height")
                .field("type", Integer.class)
                .endObject()

                // 学校
                .startArray("school")
                .startObject("school_name")
                .field("type", "keyword")
                .endObject()
                .startObject("start_year")
                .field("type", Integer.class)
                .endObject()
                .startObject("end_year")
                .field("type", Integer.class)
                .endObject()
                .startObject("school_address")
                .field("type", "text")
                .endObject()
                .endArray()

                // 公司
                .startArray("company")
                .startObject("company_name")
                .field("type", "keyword")
                .endObject()
                .startObject("start_year")
                .field("type", Integer.class)
                .endObject()
                .startObject("end_year")
                .field("type", Integer.class)
                .endObject()
                .startObject("company_address")
                .field("type", "text")
                .endObject()
                .endArray()
//
                // 居住地址
                .startObject("address")
                .field("type","text")
                .endObject()

                .endObject()

                .endObject();
        cib.addMapping(type, mapping);
        cib.execute().actionGet();
    }

    /**
     * 判断索引存不存在
     * @param index
     * @return
     */
    public static boolean indexExists(String index){

        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = adminClient.exists(request).actionGet();
        if (response.isExists()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(indexExists("yan_index_two"));
        try {
            CreateIndex("yan_index_three", 3,4, "yan_type_one");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
