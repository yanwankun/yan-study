package cn.yan.study.spring.boot.dubbo.provider.demo;

import cn.yan.study.spring.boot.dubbo.api.demo.DemoTestService;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
@Service(version = "${provider.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoTestServiceImpl implements DemoTestService {

    @Override
    public String test() {
        return "hello spring boot dubbo demo";
    }
}
