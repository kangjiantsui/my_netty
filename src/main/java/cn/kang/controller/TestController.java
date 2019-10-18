package cn.kang.controller;

import cn.kang.socket.ScoketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ScoketClient webScoketClient;

    @GetMapping("/test1")
    public void test1() {
        webScoketClient.groupSending();
    }
}
