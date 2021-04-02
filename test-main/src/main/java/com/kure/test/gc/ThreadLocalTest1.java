package com.kure.test.gc;

import java.util.concurrent.TimeUnit;

/**
 * threadlocal oom
 *
 */
public class ThreadLocalTest1 {

    static ThreadLocal<Persion> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {

        new Thread(()->{
            threadLocal.set(new Persion());

            threadLocal = null;

//            threadLocal.remove();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Persion();
        }).start();
    }
}

class Persion{
    byte[] b = new byte[1024*1024*11];
}
