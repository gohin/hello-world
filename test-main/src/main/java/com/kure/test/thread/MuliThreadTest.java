package com.kure.test.thread;

import java.util.stream.Stream;

/**
 * 多线程调试
 * right click breakPoint set 'suspend' on Thread
 * then you can selected one of the running threads.
 */
public class MuliThreadTest {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("tread1 test");
        }, "test thread1").start();

        new Thread(()->{
            System.out.println("tread2 test");
        }, "test thread2").start();

        Stream.iterate(0, i -> i+1).limit(10).forEach(key->{
            System.out.println(key);
        });

        System.out.println("main test3");
        System.out.println("main test4");
    }
}
