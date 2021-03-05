package com.kure.test.design.proxy.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MybatisJdkProxy implements InvocationHandler {

    private Object target;
    private Class clazz;

    public MybatisJdkProxy(Object target, Class clazz){
        this.target = target;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try{
            System.out.println("before proxy");
            System.out.println(clazz.getCanonicalName());
            return method.invoke(this, args);
        }catch (Exception e) {

        }finally {
            System.out.println("after proxy");
        }
        return null;
    }
}
