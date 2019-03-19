package cn.yan.study.springboot.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
@Component
public class FanoutReceiver2 {

    @RabbitListener(queues = "topic.B")
    public void process(String message) {
        System.out.println("TopicReceiverB  : " + message);
    }

    @RabbitListener(queues = "topic.A")
    public void process2(String message) {
        System.out.println("FanoutReceiverA  : " + message);
    }
}
