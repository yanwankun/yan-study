//package cn.yan.study.springboot.rabbitmq.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created with IDEA
// *
// * @author: gentlemen_k
// * @emali: test@qq.com
// **/
//@Configuration
//public class TopicRabbitConfig {
//
//    /**
//     * 定义队列名称
//     */
//    private final static String message = "topic.message";
//    private final static String messages = "topic.messages";
//
//    @Bean
//    public Queue queueMessage() {
//        return new Queue(TopicRabbitConfig.message);
//    }
//
//    @Bean
//    public Queue queueMessages() {
//        return new Queue(TopicRabbitConfig.messages);
//    }
//
//    /**
//     * 这里的exchange是交换机的名称字符串和发送消息时的名称必须相同
//     * this.rabbitTemplate.convertAndSend("exchange", "topic.1", context);
//     */
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("exchangeOne");
//    }
//
//    /**
//     * @param queueMessage 队列
//     * @param exchange     交换机
//     *                     bindings 绑定交换机队列信息
//     */
//    @Bean
//    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
//    }
//
//    @Bean
//    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
//    }
//}
