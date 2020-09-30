package com.kure.test.design.proxy;

import java.lang.reflect.Proxy;

public class HelloImpl implements Hello {

    @Override
    public void sayHi() {
        System.out.println("hi");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Hello hello = (Hello) new JdkProxy(HelloImpl.class.newInstance()).createJdkProxy();
        hello.sayHi();
    }
}
