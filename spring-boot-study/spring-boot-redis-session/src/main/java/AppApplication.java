import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created
 * User  wankunYan
 * Date  2018/4/19
 * Time  15:21
 */
@SpringBootApplication(scanBasePackages="cn.yan.test.spring.boot.redis")
@EnableRedisHttpSession
public class AppApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppApplication.class, args);
    }
}
