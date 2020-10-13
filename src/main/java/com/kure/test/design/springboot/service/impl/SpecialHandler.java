package com.kure.test.design.springboot.service.impl;

import com.kure.test.design.springboot.HandlerType;
import com.kure.test.design.springboot.domain.Order;
import com.kure.test.design.springboot.service.AbstractHandler;
import org.springframework.stereotype.Component;

@Component
@HandlerType("special")
public class SpecialHandler extends AbstractHandler {

    @Override
    public String handler(Order order) {
        return "处理特殊订单";
    }
}
