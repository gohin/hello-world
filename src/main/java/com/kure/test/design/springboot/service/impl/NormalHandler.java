package com.kure.test.design.springboot.service.impl;

import com.kure.test.design.springboot.HandlerType;
import com.kure.test.design.springboot.domain.Order;
import com.kure.test.design.springboot.service.AbstractHandler;
import org.springframework.stereotype.Component;

@Component
@HandlerType("normal")
public class NormalHandler extends AbstractHandler {

    @Override
    protected String handler(Order order) {
        return "处理普通订单";
    }
}
