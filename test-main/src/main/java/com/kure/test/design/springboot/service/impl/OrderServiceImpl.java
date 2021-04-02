package com.kure.test.design.springboot.service.impl;

import com.kure.test.design.springboot.domain.Order;
import com.kure.test.design.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private HandlerContext handlerContext;

    @Override
    public String handler(Order order) {
        return handlerContext.getHandler(order.getType()).handler(order);
    }
}
