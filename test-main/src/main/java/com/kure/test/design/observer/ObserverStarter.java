package com.kure.test.design.observer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ObserverStarter {
    public static void main(String[] args) {
        // ConfigurableApplicationContext 实现了 ApplicationEventPublisher
        final ConfigurableApplicationContext run = SpringApplication.run(ObserverStarter.class, args);

        // 通知
        run.publishEvent(new MyEvent(""));
        run.close();

    }
}
