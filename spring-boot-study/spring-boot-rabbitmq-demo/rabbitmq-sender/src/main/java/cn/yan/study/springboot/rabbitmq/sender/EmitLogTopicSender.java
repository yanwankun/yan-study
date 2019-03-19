package cn.yan.study.springboot.rabbitmq.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class EmitLogTopicSender {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv)
            throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //指定一个topic类型的exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        //这里拿到routing key
        String routingKey = "yan.test.topic.one";
        String message = "this is a test for rabbit mq topic";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        connection.close();
    }
}
