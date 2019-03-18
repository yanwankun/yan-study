//package cn.yan.study.springboot.rabbitmq.receiver;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by gentlemen_yan on 2019/3/2.
// */
//@Component
//@RabbitListener(queues = "hello")
//public class HelloReceiver {
//
//    @RabbitHandler
//    public void process(String hello) {
//        System.out.println("Receiver  : " + hello);
//    }
//
//}
