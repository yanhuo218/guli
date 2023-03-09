package com.yanhuo.serviceedu;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = {"com.yanhuo"})
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class EdoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdoApplication.class, args);
        log.info("启动成功");
    }
}
