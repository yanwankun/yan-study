//package cn.yan.study.springboot.rabbitmq.sender;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * Created by gentlemen_yan on 2019/3/2.
// */
//@Component
//public class TopicMsgSender {
//    @Autowired
//    private AmqpTemplate rabbitTemplate;
//
//    public void send() {
//        String context = "hello " + new Date();
//        System.out.println("Sender : " + context);
//        this.rabbitTemplate.convertAndSend("myExchange","yan.wan.kun", context);
//    }
//}
