package com.kure.test.thread;

public class ThreadTest extends Thread {

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();

        threadTest.start();

        new Thread(new Bar()::test).start();
    }

    @Override
    public void run(){
        System.out.println("11111");
    }
}

class Bar {
    public void test() {
        System.out.println("thread test");
    }
}
