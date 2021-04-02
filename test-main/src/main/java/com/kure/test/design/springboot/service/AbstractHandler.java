package com.kure.test.design.springboot.service;

import com.kure.test.design.springboot.domain.Order;

public abstract class AbstractHandler {

    public abstract String handler(Order order);
}
