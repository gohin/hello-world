package com.kure.test.design.proxy;

public class CglibTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        CglibTest cglibProxy = (CglibTest) new CglibProxy(CglibTest.class.newInstance()).createCglibProxy();
        cglibProxy.sayHi();
    }

    public void sayHi(){
        System.out.println("hi cglib");
    }
}

