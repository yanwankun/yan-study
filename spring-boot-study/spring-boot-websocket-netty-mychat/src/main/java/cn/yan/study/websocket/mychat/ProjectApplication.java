package cn.yan.study.websocket.mychat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HEE
 * @version 0.0.1
 */
@RestController
@SpringBootApplication
@MapperScan(basePackages =
		{
				"cn.yan.study.netty.mychat.mapper"
		})
public class ProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
}

