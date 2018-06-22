package cn.yan.test.spring.boot.redis.session.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created
 * User  wankunYan
 * Date  2018/4/19
 * Time  15:22
 */
@RestController
public class IndexController {
    @GetMapping("/index")
    public ResponseEntity index(HttpSession httpSession) {
        httpSession.setAttribute("user", "helloword");
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/helloword")
    public ResponseEntity hello(HttpSession httpSession) {
        return ResponseEntity.ok(httpSession.getAttribute("user"));
    }
}
