package com.kure.test.design.springboot;

import com.kure.test.design.springboot.domain.Order;
import com.kure.test.design.springboot.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class Starter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Starter.class, args);
        Order order = new Order();order.setType("special");
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService.handler(order));
        context.close();
    }

}
