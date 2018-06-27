package cn.yan.study.es.utils;

import cn.yan.study.es.client.EsClient;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.ReindexAction;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/26
 * Time  10:15
 */
public class EsClientUtils {

    private static TransportClient client = EsClient.getTransportClient();

    /**
     * 这个方法是创建文档的 将文档添加到服务器 并且可以被搜索
     * @param indexName 索引名称
     * @param indexType 索引类型
     * @param docId 文档id
     * @param parent 父子关系中的父节点
     * @param objMap 字段信息
     */
    public static void addDocument(String indexName, String indexType, String docId, String parent, Map<String, Object> objMap) {
        try {
            XContentBuilder xContentBuilder = jsonBuilder()
                    .startObject();
            for (Map.Entry<String, Object> entry : objMap.entrySet()) {
                xContentBuilder.field(entry.getKey(), entry.getValue());
            }
            xContentBuilder = xContentBuilder.endObject();
            IndexResponse response = client.prepareIndex(indexName, indexType, docId).setParent(parent)
                    .setSource(xContentBuilder)
                    .get();

            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * 这个是根据信息获取文档
     * @param indexName 索引名称
     * @param indexType 索引类型
     * @param docId 文档id
     */
    public static void getDocument(String indexName, String indexType, String docId) {
        GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
        System.out.println(response.toString());
    }

    /**
     * 这个是根据条件删除指定文档
     * @param indexName 索引名称
     * @param indexType 索引类型
     * @param docId 文档id
     */
    public static void deleteDocument(String indexName, String indexType, String docId) {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1").get();
        System.out.println(response.toString());
    }

    public static void updateDocument(String indexName, String indexType, String docId) throws IOException, ExecutionException, InterruptedException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("twitter");
        updateRequest.type("tweet");
        updateRequest.id("1");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("user", "yanwankun")
                .field("testField", "test")
                .endObject());
        client.update(updateRequest).get();
    }


    /**
     * 更新或者插入
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void upSertDocument() throws IOException, ExecutionException, InterruptedException {
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

    /**
     * 一次获取多个文件
     */
    public static void multiGetDocument() {
        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add("twitter", "tweet", "1")
                .add("twitter", "tweet", "2", "3", "4")
                .add("another", "type", "foo")
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
            }
        }
    }

    /**
     * 在一个调用中发多个请求
     * @throws IOException
     */
    public static void bulk() throws IOException {
        BulkRequestBuilder bulkRequest = client.prepareBulk();

// either use client#prepare, or use Requests# to directly build index/delete requests
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                        .endObject()
                )
        );

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
    }

    public static void reIndex() {
        BulkByScrollResponse response = ReindexAction.INSTANCE.newRequestBuilder(client)
                .destination("target_index")
                .filter(QueryBuilders.matchQuery("category", "xzy"))
                .get();
    }

    // todo
    public static void updateByQuery() {

    }

    /**
     * 通过查询删除
     */
    public static void deleteByQuery() {
        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("gender", "male"))
                .source("persons")
                .get();
        long deleted = response.getDeleted();
    }

    /**
     * 通过查询删除 异步执行
     */
    public static void deleteByQueryAsyn() {
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("gender", "male"))
                .source("persons") // indexName
                .execute(new ActionListener<BulkByScrollResponse>() {
                    @Override
                    public void onResponse(BulkByScrollResponse response) {
                        long deleted = response.getDeleted();
                    }
                    @Override
                    public void onFailure(Exception e) {
                        // Handle the exception
                    }
                });
    }

    /**
     * 查询
     */
    public static void search() {
        SearchResponse response = client.prepareSearch("index1", "index2")
                .setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();
    }

    // todo
    public static void aggregation() {
//        SearchResponse sr = node.client().prepareSearch()
//                .setQuery( /* your query */ )
//                .addAggregation( /* add an aggregation */ )
//                .execute().actionGet();
    }




    public static void main(String[] args) {

    }


    public static void testUpdate() throws InterruptedException, ExecutionException, IOException {
        getDocument(null,null,null);
        updateDocument(null,null,null);
        getDocument(null,null,null);
    }


}
