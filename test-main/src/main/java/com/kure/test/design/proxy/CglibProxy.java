package com.kure.test.design.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy {
    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    public Object createCglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MyMethodInterceptor());
        return enhancer.create();
    }

    private class MyMethodInterceptor implements MethodInterceptor{
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before");
            return methodProxy.invokeSuper(o, objects);
        }
    }
}
