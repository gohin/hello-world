package com.kure.test.zookeeper;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.stream.Stream;

@Component
@SpringBootApplication
public class ZookeeperTest {


    public static void main(String[] args) throws Exception{

        ConfigurableApplicationContext context = SpringApplication.run(ZookeeperTest.class);

        RestTemplate restTemplate = new RestTemplate();
        /**
         * 创建一个命名空间按为base的客户端
         */
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)  // 会话超时时间
                .connectionTimeoutMs(5000) // 连接超时时间
                .retryPolicy(new ExponentialBackoffRetry(1000, 5))
                .namespace("base")
                .build();
        client.start();
        client.blockUntilConnected();

        /**
         * zk 选举
         */
        final LeaderLatch leaderLatch = new LeaderLatch(client, "/leaders", "whoisleader");
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
        leaderLatch.await();

        Participant participant = leaderLatch.getLeader();
        if (participant.isLeader()) {
            System.out.println("participant isLeader");
            Thread.sleep(3000L);
        }

        /**
         * 服务发现
         */
        ServiceDiscovery serviceDiscovery =
                ServiceDiscoveryBuilder.builder(Object.class).client(client).basePath("/test").build();


        List<ServiceInstance> instances = (List<ServiceInstance>) serviceDiscovery.queryForInstances("service");

        instances.forEach((serviceInstance)-> {
            Object obj = restTemplate.getForObject("http://"+serviceInstance.getAddress()+":"+serviceInstance.getPort()+"/hello", Object.class);
            System.out.println(obj);
        });

        leaderLatch.close();
        client.close();
        context.close();
    }
}
