package com.nwp.appcloudclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class AppCloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppCloudClientApplication.class, args);
    }

    @RequestMapping("/hello")
    public String test(){
        return "deom测试";
    }
}
