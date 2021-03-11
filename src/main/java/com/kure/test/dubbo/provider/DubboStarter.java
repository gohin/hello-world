package com.kure.test.dubbo.provider;


import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DubboStarter {
    public static void main(String[] args) {
        SpringApplication.run(DubboStarter.class, args);
    }
}
