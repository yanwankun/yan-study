package cn.yan.study.springboot.rabbitmq;

import cn.yan.study.springboot.rabbitmq.domian.MyMsg;
import cn.yan.study.springboot.rabbitmq.sender.RabbitMqSender;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
public class ProjectApplicationTests {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private RabbitMqSender rabbitMqSender;


    @Test
    public void contextLoads() {
        rabbitMqSender.send();
    }

    @Test
    public void sendTopicMsg() {
        MyMsg myMsg = MyMsg.builder()
                .sender("yanwankun")
                .data("this is a test for topic zhuti")
                .build();

        this.rabbitTemplate.convertAndSend("my.exchange.topic","my.test.topic", JSON.toJSONString(myMsg));
    }

    @Test
    public void sendDirectMsg() {
        MyMsg myMsg = MyMsg.builder()
                .sender("yanwankun")
                .data("this is a test for topic zhuti")
                .build();

        this.rabbitTemplate.convertAndSend("my.exchange.direct","my.exchange.direct", JSON.toJSONString(myMsg));
    }

    @Test
    public void sendHeadersMsg() {
        MyMsg myMsg = MyMsg.builder()
                .sender("yanwankun")
                .data("this is a test for topic zhuti")
                .build();

        this.rabbitTemplate.convertAndSend("my.exchange.headers","my.exchange.headers", JSON.toJSONString(myMsg));
    }

    @Test
    public void sendFanoutMsg() {
        MyMsg myMsg = MyMsg.builder()
                .sender("yanwankun")
                .data("this is a test for topic zhuti")
                .senderTime(new Date())
                .build();

        this.rabbitTemplate.convertAndSend("my.exchange.fanout","my.exchange.123.fanout", JSON.toJSONString(myMsg));
    }


}
