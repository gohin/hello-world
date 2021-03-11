package com.kure.test.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kure.test.dubbo.provider.service.DemoService;
import org.springframework.stereotype.Component;

@Service
@Component
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
