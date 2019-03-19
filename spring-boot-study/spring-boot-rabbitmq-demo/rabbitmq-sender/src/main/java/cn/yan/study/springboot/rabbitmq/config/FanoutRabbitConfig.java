package cn.yan.study.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/

@Configuration
public class FanoutRabbitConfig {

    /**
     * 定义队列名称
     */
    private final static String message = "topic.A";
    private final static String messages = "topic.B";

    @Bean
    public Queue queueA() {
        return new Queue(FanoutRabbitConfig.message);
    }

    @Bean
    public Queue queueB() {
        return new Queue(FanoutRabbitConfig.messages);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("FanoutExchange");
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("queueA")Queue queueMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange);
    }

    @Bean
    Binding bindingExchangeMessages(@Qualifier("queueB")Queue queueMessages, FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange);
    }
}