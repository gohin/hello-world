package com.kure.test.zookeeper;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 分布式锁
 */
public class Zookeeper2 {

    public static void main(String[] args) throws Exception{

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.2.129:2181")
                .sessionTimeoutMs(5000)  // 会话超时时间
                .connectionTimeoutMs(5000) // 连接超时时间
                .retryPolicy(new ExponentialBackoffRetry(1000, 5))
                .namespace("base")
                .build();
        client.start();
        client.blockUntilConnected();

        InterProcessMutex lock = new InterProcessMutex(client, "/locks/lock");

        System.out.println("等待获取锁");
        lock.acquire();
        System.out.println("获取锁 睡眠="+ 3000L);

        Thread.sleep(30000L);

        lock.release();
        System.out.println("释放锁");


        final LeaderLatch leaderLatch = new LeaderLatch(client, "/leaders");
        leaderLatch.addListener(new LeaderLatchListener(){
            @Override
            public void isLeader() {
                System.out.println("isLeader");
            }

            @Override
            public void notLeader() {
                System.out.println("notLeader");
            }
        });

        leaderLatch.start();
        System.out.println("wait for lock");
        leaderLatch.await();

    }
}
