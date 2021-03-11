package com.kure.test;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDubbo
public class ConsumerStart {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerStart.class, args);
    }
}