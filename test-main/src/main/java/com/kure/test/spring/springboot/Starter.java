package com.kure.test.spring.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 1.springboot默认会扫描启动类所在的包及其子包；
 * 2.不在自动扫描路径下，需要修改自定义扫描包路径。
 *
 * ControllerAdvice注解 搭配ExceptionHandler
 */
@SpringBootApplication
public class Starter {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        springApplication.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = SpringApplication.run(Starter.class, args);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setBeanResolver(new BeanFactoryResolver(context));
        ctx.addPropertyAccessor(new MapAccessor());
        EvaluationContext evaluationContext = ctx;
        ExpressionParser parser = new SpelExpressionParser();
        parser.parseExpression("@starter.sayHi()").getValue(evaluationContext);

        context.close();
    }

    public void sayHi(){
        System.out.println("say hi");
    }
}
