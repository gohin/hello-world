package com.kure.test;

public class VolatileTest {

    public volatile int i = 0;

    public void increase() {
        i++;
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<1000;j++)
                    test.increase();
            }).start();
        }

        while(Thread.activeCount()>1)
            Thread.yield();
        System.out.println(test.i);
    }
}
