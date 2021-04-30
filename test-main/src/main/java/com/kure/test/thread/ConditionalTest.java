package com.kure.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionalTest {
    public static void main(String[] args) {
        Data3 data3=new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        },"C").start();
    }
}
class Data3{

    private Lock lock=new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number=1; //1a 2b 3c
    public void printA(){
        lock.lock();
        try {
            while (number!=1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>AAAAAA");
            number=2;
            condition2.signal();//唤醒指定的人干活
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while (number!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>BBBBBB");
            number=3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while (number!=3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>CCCCCC");
            number=1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
