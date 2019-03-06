package cn.yan.study.springboot.websocket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    public String helloWord() {
        return "hello word";
    }

}
