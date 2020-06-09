package com.nwp.appcloudconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 消费者
 */
//关闭Spring Security登录拦截
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class AppCloudConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppCloudConsumerApplication.class, args);
    }

}