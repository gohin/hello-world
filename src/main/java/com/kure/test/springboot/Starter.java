package com.kure.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 1.springboot默认会扫描启动类所在的包及其子包；
 * 2.不在自动扫描路径下，需要修改自定义扫描包路径。
 *
 * ControllerAdvice注解 搭配ExceptionHandler
 */
@SpringBootApplication
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
