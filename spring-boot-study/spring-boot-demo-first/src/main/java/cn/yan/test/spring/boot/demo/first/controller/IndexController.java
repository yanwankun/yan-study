package cn.yan.test.spring.boot.demo.first.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * Created
 * User  wankunYan
 * Date  2018/4/18
 * Time  18:36
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public ResponseEntity helloWord() {
        return ResponseEntity.ok("hello word");
    }

}
