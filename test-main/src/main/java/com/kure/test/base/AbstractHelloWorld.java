package com.kure.test.base;

/**
 * @author Lenovo
 */
public abstract class AbstractHelloWorld {
    public static void main(String[] args) {
        AbstractHelloWorld abstractHelloWorld = new AbstractHelloWorld() {
            @Override
            void sayHello(String name) {
                System.out.println("hello" + name);
            }
        };
        abstractHelloWorld.sayHello();

        System.out.println("1111");
    }

    public void sayHello(){

    }

    abstract void sayHello(String name);
}
