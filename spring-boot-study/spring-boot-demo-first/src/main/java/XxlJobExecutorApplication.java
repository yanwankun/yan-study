import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author doyutu
 * @email 859898972@qq.com
 */
@SpringBootApplication
public class XxlJobExecutorApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XxlJobExecutorApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(XxlJobExecutorApplication.class, args);
	}

}