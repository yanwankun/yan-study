package cn.yan.study.springboot.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created with IDEA
 *
 * @author: wankunyan
 * @emali: yanwankunde@qq.com
 **/
@SpringBootApplication(scanBasePackages="cn.yan.study")
@EnableScheduling
public class AppApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppApplication.class, args);
    }
}
