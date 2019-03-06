package cn.yan.study.spring.boot.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DubboConsumerApplication
 * 消费者启动类
 * @author xiaoze
 * @date 2018/6/7
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = "cn.yan.study.spring.boot.dubbo")
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
}
