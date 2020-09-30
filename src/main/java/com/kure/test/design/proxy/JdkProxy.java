package com.kure.test.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    private Object target;
    public JdkProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try{
            System.out.println("before proxy");
            return method.invoke(target, args);
        }catch (Exception e) {

        }finally {
            System.out.println("after proxy");
        }
        return null;
    }

    public Object getJdkProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);
    }
}
