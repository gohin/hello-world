package com.kure.test.design.springboot;

import com.kure.test.design.springboot.domain.Order;
import com.kure.test.design.springboot.service.OrderService;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
@EnableAspectJAutoProxy
public class Starter {

    public static void main(String[] args) {
        SpringApplication springApplication =  new SpringApplication(Starter.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = springApplication.run(args);

        Order order = new Order();order.setType("special");
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService.handler(order));
        context.close();
    }

}
