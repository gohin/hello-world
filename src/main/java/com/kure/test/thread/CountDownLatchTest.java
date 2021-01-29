package com.kure.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5, 1,TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    public static void main(String[] args) throws InterruptedException {
        ArrayList list = new ArrayList();
        for (int i =0; i< 10000; i++) {
            list.add(i);
        }

        int index = 0;
        CountDownLatch countDownLatch;
        for(int i= 0; i< 3; i++) {
            int nextIdx = Math.min(index + 1000, list.size());
            List a = list.subList(index, nextIdx);
            countDownLatch = new CountDownLatch(1);
            threadPoolExecutor.submit(new MyThread(a,countDownLatch));
            countDownLatch.await();
            index = nextIdx;
        }
        threadPoolExecutor.shutdown();
    }
}

class MyThread implements Runnable{

    private List execList;
    private CountDownLatch countDownLatch;

    public MyThread(List list, CountDownLatch countDownLatch) {
        this.execList = list;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            execList.forEach(System.out::println);
        }finally {
            countDownLatch.countDown();
        }

    }
}
