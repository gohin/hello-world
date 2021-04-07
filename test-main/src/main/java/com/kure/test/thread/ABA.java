package com.kure.test.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * unsafe java.compareAndSwapInt native - unsafe.cpp
 * Atomic::cmpxhg -asm:cmpxchg -cpu的汇编指令
 * cas操作cpu本身有指令支持 cmpxchg 不保证原子性
 * LOCK_IF_MF cmpxchg === lock_cmpxchg 多核时 加锁
 *
 * cas效率是否一定比悲观锁效率高
 * 什么时候用cas(线程持续时间短使用) 什么时候用悲观锁
 * 优先使用synchronized解决问题的优先使用 队列不消耗cpu
 * cas 自旋 浪费cpu
 *
 * jdk1.5之后 synchronized内部有锁升级的过程
 * 偏向锁（可重入锁）-自旋锁（轻量级锁 cas 无锁）-重量级锁
 *
 */
public class ABA {

    private static AtomicInteger atomicInt = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<Integer>(100, 0);

    public synchronized static void main(String[] args) throws InterruptedException {
        Thread intT1 = new Thread(() -> {
            atomicInt.compareAndSet(100, 101);
            atomicInt.compareAndSet(101, 100);
        });

        Thread intT2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean c3 = atomicInt.compareAndSet(100, 101);
            System.out.println(c3);        //true
        });

        intT1.start();
        intT2.start();
        intT1.join();
        intT2.join();

        Thread refT1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedRef.compareAndSet(100, 101,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+1);
            atomicStampedRef.compareAndSet(101, 100,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+1);
        });

        Thread refT2 = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp();
            System.out.println("before sleep : stamp = " + stamp);    // stamp = 0
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after sleep : stamp = " + atomicStampedRef.getStamp());//stamp = 1
            boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp+1);
            System.out.println(c3);        //false
        });
        refT1.start();
        refT2.start();
    }
}