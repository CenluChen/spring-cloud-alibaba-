package com.cenlu.nacos.discovery.client.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class TestController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public String test(){
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        String result= restTemplate.getForObject("http://alibaba-nacos-discovery-server/hello?msg=nacos",String.class);
        return  " return: " + result;
    }

    @Bean@LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
