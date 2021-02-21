package com.kure.test.thread;

/**
 * 死锁
 * jstack pid
 */

public class DeadLockDemo implements Runnable {
    int flag = 0;

    static Object obj1 = new Object();
    static Object obj2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        DeadLockDemo d1 = new DeadLockDemo();
        DeadLockDemo d2 = new DeadLockDemo();
        d1.flag = 1;
        new Thread(d1).start();

        Thread.sleep(1000);
        d2.flag = 0;
        new Thread(d2).start();
    }

    @Override
    public void run() {

        if (flag == 1) {
            synchronized (obj1) {
                System.out.println("线程"+ Thread.currentThread().getName() +"获取锁obj1");
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "醒来->准备获取 obj2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj2) {
                    System.out.println("线程"+ Thread.currentThread().getName() +"获取锁obj2");
                }
            }


        }

        if (flag == 0) {
            synchronized (obj2) {
                System.out.println("线程"+ Thread.currentThread().getName() +"获取锁obj2");
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "醒来->准备获取 obj1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println("线程"+ Thread.currentThread().getName() +"获取锁obj1");
                }
            }
        }
    }
}
