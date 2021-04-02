package com.kure.test.design.proxy;

public class RealSubject implements Subject {

    @Override
    public void sayHi() {
        System.out.println("hi");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Subject hello1 = (Subject) new MybatisJdkProxy(RealSubject.class.newInstance(), RealSubject.class).createJdkProxy();
        hello1.sayHi();

//        Subject hello = (Subject) new JdkProxy(RealSubject.class.newInstance()).createJdkProxy();
//        hello.sayHi();
    }
}
