package com.kure.test.spring.spel;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpelTest {

    public static void main(String[] args) {
        Inventor inventor = new Inventor("kure", new Date(), "626");

        Inventor kure1 = new Inventor("kure1", new Date(), "626");
        Inventor kure2 = new Inventor("kure2", new Date(), "626");
        Inventor kure3 = new Inventor("kure3", new Date(), "626");
        List<Inventor> inventors = Arrays.asList(kure1, kure2, kure3);

        Map<String, Inventor> map = new HashMap<>();
        map.put("kure1", kure1);
        map.put("kure2", kure2);
        map.put("kure3", kure3);
        StandardEvaluationContext context = new StandardEvaluationContext();
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("inventor1", inventor);
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));

        context.setVariable("getInt", SpelTest.class.getMethods()[1]);
        context.setVariable("inventor", new Inventor("kure", new Date(), "626"));
        context.setVariable("inventors", inventors);
        context.setVariable("maps", map);

        ExpressionParser parser = new SpelExpressionParser();

        // 获取bean
        System.out.println(parser.parseExpression("@inventor1.name").getValue(context));

        // list
        System.out.println("list=" + parser.parseExpression("#inventors[2].name").getValue(context));

        // map
        System.out.println("map=" + parser.parseExpression("#maps['kure1'].name").getValue(context));

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

