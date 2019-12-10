package com.cenlu.nacos.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/hello")
    public String hello(String msg){
        log.info("invoke msg = "+ msg);
        return "hello" + msg;
    }
}
