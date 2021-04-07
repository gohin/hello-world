package com.kure.test;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 单一的应用架构(ORM)
 *
 * MVC 垂直应用架构  (用户 订单 商品 支付 物流)
 *
 * RPC remote procedure call (核心业务抽取出来 前后端分离)
 *
 * SOA service-oriented Architecture 服务治理
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDubbo
public class ProviderStart {
    public static void main(String[] args) {
        SpringApplication.run(ProviderStart.class, args);
    }
}