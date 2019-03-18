package cn.yan.study.springboot.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/

@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiver {
    @RabbitHandler
    public void process(String message) {
        System.out.println("TopicReceiver (routing_key is \"topic.#\") : " + message);
    }
}
