package cn.yan.test.spring.boot.demo.first.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created
 * User  wankunYan
 * Date  2018/4/18
 * Time  18:36
 */
@RestController
@RequestMapping("/test/")
public class IndexController {

    @GetMapping("hello")
    public String helloWord() {
        return "hello world";
    }

}
