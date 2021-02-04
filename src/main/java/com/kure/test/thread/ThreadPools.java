package com.kure.test.thread;

import java.util.concurrent.*;

/**
 * 线程池如何设置大小
 * 什么是IO密集型？
 * 比如：频繁读取磁盘上的数据，或者需要通过网络远程调用接口。
 * 什么是CPU密集型？
 * 比如：非常复杂的调用，循环次数很多，或者递归调用层次很深等。
 * IO密集型配置线程数经验值是：2N，其中N代表CPU核数。
 * CPU密集型配置线程数经验值是：N + 1，其中N代表CPU核数。
 * 我的电脑是8核16线程
 */
public class ThreadPools implements Runnable{

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = null;
        try {
            /**
             * 线程池拒绝策略
             * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
             * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
             * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
             * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
             */
            threadPoolExecutor = new ThreadPoolExecutor(5, 10, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), new ThreadPoolExecutor.AbortPolicy());
//            threadPoolExecutor.allowCoreThreadTimeOut(true); // 核心线程超时时间
//            threadPoolExecutor.submit(new ThreadPools()); // 有返回值

            for(int i = 0; i < 10000; i++) {
                threadPoolExecutor.submit(new ThreadPools()); // 无返回值
                System.out.println(i);
            }


            int availableProcessors = Runtime.getRuntime().availableProcessors();
            System.out.println(availableProcessors);

            Executors.newCachedThreadPool(); // 最大线程数为 Integer.MAX_VALUE
            Executors.newFixedThreadPool(4); // 核心线程数 == 最大线程数， 队列长度为 Integer.MAX_VALUE
            Executors.newScheduledThreadPool(5); // 最大线程数为 Integer.MAX_VALUE
            Executors.newSingleThreadExecutor(); // 单线程
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(threadPoolExecutor != null)
                threadPoolExecutor.shutdown();
        }
        
    }

    @Override
    public void run() {
        System.out.println("thread name" + Thread.currentThread().getName());
    }
}
