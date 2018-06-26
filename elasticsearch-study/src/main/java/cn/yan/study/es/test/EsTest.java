package cn.yan.study.es.test;

import cn.yan.study.es.client.EsClient;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/22
 * Time  11:33
 */
public class EsTest {

    TransportClient client = EsClient.getTransportClient();

    public static void main(String[] args) throws IOException {
        new EsTest().getDocument();
    }

    public void search_1() {
//        SearchResponse response = client.prepareSearch("index1", "index2")
//                .setTypes("type1", "type2")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
//                .setFrom(0).setSize(60).setExplain(true)
//                .get();
    }

    public void convertJson() {
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("user", "kimchy")
                    .field("postDate", new Date())
                    .field("message", "trying out Elasticsearch")
                    .endObject();
            String json = Strings.toString(builder);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个方法是创建索引
     * @throws IOException
     */
    public void createIndex() throws IOException {

        /**
         * 索引名称 twitter
         * type tweet
         * id 1
         *
         *
         */
        IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();

        System.out.println(response.toString());
    }

    public void createIndex2() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

        IndexResponse response1 = client.prepareIndex("twitter", "tweet")
                .setSource(json, XContentType.JSON)
                .get();
    }

    public void getApi() {
        GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
    }

    public void deleteApi() {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1").get();
    }

    public void updateIndex() throws IOException, ExecutionException, InterruptedException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("index");
        updateRequest.type("type");
        updateRequest.id("1");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("gender", "male")
                .endObject());
        client.update(updateRequest).get();
    }

    public void updateIndex2() {
//        client.prepareUpdate("ttl", "doc", "1")
//                .setScript(new Script("ctx._source.gender = \"male\""  , ScriptService.ScriptType.INLINE, null, null))
//                .get();
//
//        try {
//            client.prepareUpdate("ttl", "doc", "1")
//                    .setDoc(jsonBuilder()
//                            .startObject()
//                            .field("gender", "male")
//                            .endObject())
//                    .get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void upSert() throws IOException, ExecutionException, InterruptedException {
        IndexRequest indexRequest = new IndexRequest("index", "type", "1")
                .source(jsonBuilder()
                        .startObject()
                        .field("name", "Joe Smith")
                        .field("gender", "male")
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest("index", "type", "1")
                .doc(jsonBuilder()
                        .startObject()
                        .field("gender", "male")
                        .endObject())
                .upsert(indexRequest);
        client.update(updateRequest).get();
    }

    public void getDocument() {
        GetResponse response = client.prepareGet("twitter", "tweet", "2").get();
        System.out.println(response.toString());
    }

    public void deleteDocument() {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1").get();
    }

    public void testUpdate() throws IOException, ExecutionException, InterruptedException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("twitter");
        updateRequest.type("tweet");
        updateRequest.id("1");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("user", "yanwankun")
                .endObject());
        client.update(updateRequest).get();
    }
}
