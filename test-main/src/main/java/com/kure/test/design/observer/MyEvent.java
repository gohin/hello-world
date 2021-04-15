package com.kure.test.design.observer;

import org.springframework.context.ApplicationEvent;

/**
 * 主题
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
