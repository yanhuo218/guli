package com.yanhuo.servicecms;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.yanhuo"}) //指定扫描位置
@MapperScan("com.yanhuo.servicecms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
        log.info("启动成功");
    }
}