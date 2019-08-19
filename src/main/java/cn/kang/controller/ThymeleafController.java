package cn.kang.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
    @RequestMapping("/hello")
    public String hello() {
        return "protoDemo";
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return "webSocketDemo1";
    }
}
