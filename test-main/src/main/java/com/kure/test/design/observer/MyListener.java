package com.kure.test.design.observer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 观察者模式
 */
@Component
public class MyListener implements ApplicationListener {
    /**
     *
     * 事件处理
     */
    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MyEvent) {
            // do sth;
            System.out.println("event.getSource()=" + event.getSource());
            System.out.println("MyEvent do something");
        }
    }
}
