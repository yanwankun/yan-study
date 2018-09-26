import cn.yan.test.spring.boot.database.controller.IndexFilter;
import cn.yan.test.spring.boot.database.controller.IndexListener;
import cn.yan.test.spring.boot.database.controller.IndexServlet;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

/**
 * Created
 * User  wankunYan
 * Date  2018/4/18
 * Time  18:34
 */
@SpringBootApplication(scanBasePackages="cn.yan.test.spring.boot.database")
//@ServletComponentScan(value = "cn.yan.demo.spring.boot.database.controller")
public class AppApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new IndexServlet());
        registration.addUrlMappings("/hello");
        return registration;
    }

    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new IndexFilter());
        registration.addUrlPatterns("/");
        return registration;
    }
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new IndexListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public EmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() throws IOException {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    public Connector httpConnector() throws IOException {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol http11NioProtocol = (Http11NioProtocol) connector.getProtocolHandler();
        connector.setPort(8080);
        //设置最大线程数
        http11NioProtocol.setMaxThreads(100);
        //设置初始线程数  最小空闲线程数
        http11NioProtocol.setMinSpareThreads(20);
        //设置超时
        http11NioProtocol.setConnectionTimeout(5000);
        return connector;
    }

//    @Profile("jetty")
//    @Bean
//    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(
//            JettyServerCustomizer jettyServerCustomizer) {
//        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
//        factory.addServerCustomizers(jettyServerCustomizer);
//        return factory;
//    }
//
//
//    @Bean
//    public JettyServerCustomizer jettyServerCustomizer() {
//        return server -> {
//            // Tweak the connection config used by Jetty to handle incoming HTTP
//            // connections
//            final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
//            threadPool.setMaxThreads(100);
//            threadPool.setMinThreads(20);
//        };
//    }
}
