package com.kure.test.design.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class ProxyFactoryTest implements MethodInterceptor{

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addInterface(SubjectTest.class);
        proxyFactory.addAdvice(new ProxyFactoryTest());
        proxyFactory.setTarget(ProxyTest.class.newInstance());
        SubjectTest subjectTest = (SubjectTest) proxyFactory.getProxy();
        subjectTest.sayHi();
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("invoke");
        invocation.proceed();
        return null;
    }
}

class ProxyTest implements SubjectTest{
    @Override
    public void sayHi() {
        System.out.println("1111111");
    }
}
