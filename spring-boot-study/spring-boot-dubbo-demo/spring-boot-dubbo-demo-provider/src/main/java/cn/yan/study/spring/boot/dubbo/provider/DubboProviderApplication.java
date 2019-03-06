package cn.yan.study.spring.boot.dubbo.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DubboProviderApplication
 * 服务提供启动类
 * @author xiaoze
 * @date 2018/6/7
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = "cn.yan.study.spring.boot.dubbo")
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
