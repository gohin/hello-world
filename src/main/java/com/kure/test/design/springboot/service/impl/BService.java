package com.kure.test.design.springboot.service.impl;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BService {
    @Autowired
    private AService aService;
}
