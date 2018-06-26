package cn.yan.study.es.client;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/22
 * Time  14:49
 */
public class EsClient {

    // 取得实例
    public static synchronized TransportClient getTransportClient() {
        try {
            Settings settings = Settings.builder()
                    .put("client.transport.sniff", true)
                    .put("cluster.name", "myClusterName").build();

            // 可以设置到PreBuiltTransportClient里面
            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

            return client;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        TransportClient client = EsClient.getTransportClient();
        System.out.println("111");
    }

}
