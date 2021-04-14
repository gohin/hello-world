package com.kure.test.design.observer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 观察者模式
 */
@Component
public class MyEventListener {
    /**
     *
     * 事件处理
     */
    @Async
    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MyEvent) {
            // do sth;
            System.out.println("event.getSource()=" + event.getSource());
            System.out.println("MyEvent do something111111111111");
        }
    }
}
