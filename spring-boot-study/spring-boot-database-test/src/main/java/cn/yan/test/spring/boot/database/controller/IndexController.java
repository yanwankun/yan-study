package cn.yan.test.spring.boot.database.controller;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created
 * User  wankunYan
 * Date  2018/4/20
 * Time  11:10
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public ResponseEntity helloWord() {
        return ResponseEntity.ok("hello word");
    }

}
