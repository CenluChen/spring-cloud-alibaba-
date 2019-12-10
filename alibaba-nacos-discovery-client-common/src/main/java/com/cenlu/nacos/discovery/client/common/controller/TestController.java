package com.cenlu.nacos.discovery.client.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class TestController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/test")
    public String test(){
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        String result= restTemplate.getForObject("http://alibaba-nacos-discovery-server/hello?msg=nacos",String.class);
        return  " return: " + result;
    }
    //使用Feign
    //1.在pom.xml中增加依赖
    //2.定义Feign客户端和使用

    @Autowired
    Client client;
    @GetMapping("/test2")
    public String test2(){
        String result = client.hello("nacos");
        return "Return : "+ result;
    }

    @FeignClient("alibaba-nacos-discovery-server")
    interface Client{

        @GetMapping("/hello")
        String hello(@RequestParam(name = "msg") String msg);
    }


}
