package com.kure.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    public static void main(String[] args) {
        A a = new B();
        a.sayHi();
    }

}

class A{
    public void sayHi(){
        System.out.println("sayHi A");
    }
}

class B extends A{
    public void sayHello(){
        System.out.println("sayHello");
    }

    public void sayHi(){
        System.out.println("sayHi B");
    }
}
