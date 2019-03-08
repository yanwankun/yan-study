package cn.yan.study.springboot.rabbitmq.sender;

import cn.yan.study.springboot.rabbitmq.domian.MyMsg;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */
@Component
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        MyMsg myMsg = MyMsg.builder()
                .sender("yanwankun")
                .data("this is a test for topic zhuti")
                .build();

        this.rabbitTemplate.convertAndSend("yan.wan.kun", JSON.toJSONString(myMsg));
    }
}
