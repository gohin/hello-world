package com.kure.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCTest {

    private static int state = 0; // 类变量 线程不安全
    private static Lock lock = new ReentrantLock();

    private String name; // 成员变量 线程不安全


    public static void main(String[] args) {
        // args a  局部变量 线程安全
        Thread a = new Thread(()->{
                for(int i = 0; i < 10;) {
                    lock.lock();
                    try {
                        while (state % 3 == 0) {
                            System.out.println("A");
                            state++;
                            i++;
                        }
                    } catch (Exception e) {
                    } finally {
                        lock.unlock();
                    }
                }
        },"a");


        Thread b = new Thread(()->{
            for(int i = 0; i < 10;) {
                lock.lock();
                try {
                    while (state % 3 == 1) {
                        System.out.println("B");
                        state++;
                        i++;
                    }
                } catch (Exception e) {
                } finally {
                    lock.unlock();
                }
            }
        },"b");


        Thread c = new Thread(()->{
            for(int i = 0; i < 10;) {
                lock.lock();
                try {
                    while (state % 3 == 2) {
                        System.out.println("C");
                        state++;
                        i++;
                    }
                } catch (Exception e) {
                } finally {
                    lock.unlock();
                }
            }
        },"c");

        a.start();
        b.start();
        c.start();
    }

}
