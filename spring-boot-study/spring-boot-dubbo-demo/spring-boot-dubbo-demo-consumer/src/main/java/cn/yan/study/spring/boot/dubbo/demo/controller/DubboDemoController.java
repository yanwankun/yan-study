package cn.yan.study.spring.boot.dubbo.demo.controller;

import cn.yan.study.spring.boot.dubbo.api.demo.DemoTestService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/

@RestController
public class DubboDemoController {

    @Reference(
            version = "${consumer.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    private DemoTestService demoTestService;


    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return demoTestService.test();
    }

}
