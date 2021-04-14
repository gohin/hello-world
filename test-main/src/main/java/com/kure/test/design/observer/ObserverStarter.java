package com.kure.test.design.observer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 观察者模式：定义对象间的一种一对多依赖关系，
 * 使得每当一个对象状态发生改变时，
 * 其相关依赖对象皆得到通知并被自动更新 对象行为型模式
 */
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
