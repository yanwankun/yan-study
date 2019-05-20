package cn.yan.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
@RestController
public class TestController {

    @RequestMapping("getTime")
    public long getTimeInfo() {
        return new Date().getTime()/1000L;
    }

    @RequestMapping("hello")
    public String hello(String name) {
        return "hello : " + name;
    }
}
