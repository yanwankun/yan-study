package cn.yan.study.springboot.websocket.config;

import cn.yan.study.springboot.websocket.standard.ServerEndpointExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
