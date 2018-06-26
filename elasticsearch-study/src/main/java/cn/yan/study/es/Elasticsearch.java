package cn.yan.study.es;

/**
 * Created 没有用
 * User  wankunYan
 * Date  2018/6/26
 * Time  14:21
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cn.yan.study.es.client.EsClient;
import cn.yan.study.es.index.Index;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;

public class Elasticsearch {
    private Client client;
    private IndicesAdminClient adminClient;
    /**
     * 集群配置初始化方法
     * @throws Exception
     */
    private void init() throws Exception{
        client = EsClient.getTransportClient();
    }
    /**
     * 构造方法
     */
    public Elasticsearch() {
        try {
            init();
        } catch (Exception e) {
            System.out.println("init() exception!");
            e.printStackTrace();
        }
        adminClient = client.admin().indices();
    }
    /**
     * 判断集群中{index}是否存在
     * @param index
     * @return 存在（true）、不存在（false）
     */
    public boolean indexExists(String index){
        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = adminClient.exists(request).actionGet();
        if (response.isExists()) {
            return true;
        }
        return false;
    }
    /**
     * 读取es配置信息
     * @return
     */
    private Properties readElasticsearchConfig() {
        Properties properties = new Properties();
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("elasticsearch.properties");
            properties.load(new InputStreamReader(is,"UTF-8"));
        } catch (IOException e) {
            System.out.println("readEsConfig exception!");
            e.printStackTrace();
        }
        return properties;
    }
    /**
     * 获取要创建的index列表
     * @return List<Index>
     */
    public List<Index> getIndexList(){
        List<Index> indexList = new ArrayList<>();
        Index index1 = new Index("index_one", "type_one", 4, 5, "{\"initType\":{\"properties\":{\"date\":{\"format\":\"yyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\",\"type\":\"date\"},\"name\":{\"type\":\"String\"}}}}");
        indexList.add(index1);
        return indexList;
    }
    /**
     * 创建Index
     */
    public void CreateIndex(){
        int i = 0;
        List<Index> list = getIndexList();
        IndicesAdminClient adminClient = client.admin().indices();
        for(Index index : list){
            if (indexExists(index.getIndex())) {
                continue;
            }
            adminClient.prepareCreate(index.getIndex())
                    .setSettings(Settings.builder().put("index.number_of_shards", index.getNumber_of_shards()).put("index.number_of_replicas", index.getNumber_of_replicas()))
                    .addMapping(index.getType(), index.getFieldJson())
                    .get();
            i++;
        }
        System.out.println("index creation success! create "+i+" index");

    }
    public static void main(String[] args) {
        Elasticsearch es = new Elasticsearch();
        es.CreateIndex();
    }
}
