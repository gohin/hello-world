package com.kure.test.spring.spel;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;

public class SpelTest {

    public static void main(String[] args) {
        Inventor inventor = new Inventor("kure", new Date(), "626");
        StandardEvaluationContext context = new StandardEvaluationContext();
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("inventor1", inventor);
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));

        context.setVariable("getInt", SpelTest.class.getMethods()[1]);
        context.setVariable("inventor", new Inventor("kure", new Date(), "626"));
        ExpressionParser parser = new SpelExpressionParser();

        // 获取bean
        System.out.println(parser.parseExpression("@inventor1.name").getValue(context));

        System.out.println(parser.parseExpression("5 == 4").getValue());
        System.out.println(parser.parseExpression("5 eq 5").getValue());
        // 获取变量
        System.out.println(parser.parseExpression("#inventor.birthdate").getValue(context, Date.class));

        // 静态方法
        Integer pupin = parser.parseExpression("#getInt() * 5").getValue(
                context, Integer.class);
        System.out.println(pupin);
    }

    public static int getInt(){
        return 1 * 4;
    }
}

