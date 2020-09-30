package com.kure.test.design.proxy;

public class RealSubject implements Subject {

    @Override
    public void sayHi() {
        System.out.println("hi");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Subject hello = (Subject) new JdkProxy(RealSubject.class.newInstance()).createJdkProxy();
        hello.sayHi();
    }
}
