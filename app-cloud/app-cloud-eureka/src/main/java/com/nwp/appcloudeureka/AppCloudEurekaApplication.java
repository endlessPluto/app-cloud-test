package com.nwp.appcloudeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppCloudEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppCloudEurekaApplication.class, args);
    }

}
