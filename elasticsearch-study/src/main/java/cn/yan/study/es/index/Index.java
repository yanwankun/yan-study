package cn.yan.study.es.index;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/26
 * Time  14:17
 */
public class Index {
    /**
     * 索引名
     */
    private String index;
    /**
     * type表名
     */
    private String type;
    /**
     * 分片数
     */
    private Integer number_of_shards;
    /**
     * 备份数
     */
    private Integer number_of_replicas;
    /**
     * 字段类型
     */
    private String fieldJson;

    public Index(String index, String type, Integer number_of_shards, Integer number_of_replicas, String fieldJson) {
        this.index = index;
        this.type = type;
        this.number_of_shards = number_of_shards;
        this.number_of_replicas = number_of_replicas;
        this.fieldJson = fieldJson;
    }

    public Index() {
    }

    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFieldJson() {
        return fieldJson;
    }
    public void setFieldJson(String fieldJson) {
        this.fieldJson = fieldJson;
    }
    public Integer getNumber_of_shards() {
        return number_of_shards;
    }
    public void setNumber_of_shards(Integer number_of_shards) {
        this.number_of_shards = number_of_shards;
    }
    public Integer getNumber_of_replicas() {
        return number_of_replicas;
    }
    public void setNumber_of_replicas(Integer number_of_replicas) {
        this.number_of_replicas = number_of_replicas;
    }
}
