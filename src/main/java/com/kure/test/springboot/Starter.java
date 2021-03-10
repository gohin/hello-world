package com.kure.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.springboot默认会扫描启动类所在的包及其子包；
 * 2.不在自动扫描路径下，需要修改自定义扫描包路径。
 *
 * ControllerAdvice注解 搭配ExceptionHandler
 *
 *
 * spring 容器描述
 *
 * 加载配置文件->解析->封装beanDefintion对象->实例化->完整对象->使用
 *
 * 通过抽象类提供的抽象方法，从配置文件 注解等来获取beanDefintion
 * 后置处理 beanFactoryProcessor 来装配变量属性
 *
 * ConfigurationClassPrase 来解析注解
 *
 * bean创建过程
 * getBean doGetBean createBean createBeanInstance
 *
 * 三级缓存 DefaultSingletonBeanRegistry
 * singletonFactories
 * earlySingletonObjects
 * singletonFactories  getbeanEarlyReference
 *
 * 只使用一级缓存行不行？ 不行，可能取到半成品
 * 只使用二级缓存行不行 ？ 行，可以解决循环依赖问题
 * 那三级缓存有什么作用？
 * aop时，有可能会用代理对象替换非代理对象，如果没有三级缓存的话
 * 那么就无法得到代理对象。
 * 三级缓存完成了代理对象替换非代理对象的工作，
 * 确定返回的是唯一的对象
 * 三级缓存是为了解决aop代理产生的循环依赖问题
 */
@SpringBootApplication
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
