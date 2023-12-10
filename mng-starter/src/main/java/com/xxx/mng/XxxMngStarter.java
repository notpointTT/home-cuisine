package com.xxx.mng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.xxx.mng")
public class XxxMngStarter {
    public static void main(String[] args) {
        SpringApplication.run(XxxMngStarter.class, args);
    }
}