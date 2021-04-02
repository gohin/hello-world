package com.kure.test.design.springboot.service.impl;

import com.google.common.collect.Maps;
import com.kure.test.design.springboot.HandlerType;
import com.kure.test.design.springboot.service.AbstractHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HandlerContext implements InitializingBean {

    private Map<String, AbstractHandler> handlerMap = Maps.newHashMapWithExpectedSize(3);

    @Autowired
    private List<AbstractHandler> handlers;

    public AbstractHandler getHandler(String handlerType){
        return handlerMap.get(handlerType);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (handlers == null) {
            return;
        }
        handlers.forEach(clazz -> {
            handlerMap.put(AnnotationUtils.findAnnotation(clazz.getClass(), HandlerType.class).value(), clazz);
        });

    }
}
