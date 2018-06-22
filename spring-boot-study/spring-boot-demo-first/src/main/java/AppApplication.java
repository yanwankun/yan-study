import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created
 * User  wankunYan
 * Date  2018/4/18
 * Time  18:34
 */
@SpringBootApplication(scanBasePackages="cn.yan.test.spring.boot.demo.first")
public class AppApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppApplication.class, args);
    }
}
