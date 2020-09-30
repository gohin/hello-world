package com.kure.test.design.proxy;

import java.lang.reflect.Proxy;

public class HelloImpl implements Hello {

    @Override
    public void sayHi() {
        System.out.println("hi");
    }

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        Hello helloProxy = (Hello) new JdkProxy(hello).getJdkProxy();
        helloProxy.sayHi();
    }
}
