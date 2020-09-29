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
                .connectString("192.168.193.51:2182")
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


//public class ProxyHandler implements InvocationHandler{
//    private Object object;
//    public ProxyHandler(Object object){
//        this.object = object;
//    }
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("Before invoke "  + method.getName());
//        method.invoke(object, args);
//        System.out.println("After invoke " + method.getName());
//        return null;
//    }
//}

//    public static void main(String[] args) {
//        HelloInterface hello = new Hello();
//
//        InvocationHandler handler = new ProxyHandler(hello);
//
//        HelloInterface proxyHello = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);
//
//        proxyHello.sayHello();
//    }



//实现InvocationHandler接口,实现invoke方法
//public class HuGeProxy2 implements InvocationHandler{
//
//    private Star hg = new HuGe();
//
//    public Star getProcxy(){
//        return (Star)Proxy.newProxyInstance(
//                getClass().getClassLoader(),
//                hg.getClass().getInterfaces(),
//                this);
//    }
//
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//        if(method.getName().equals("sing")){
//            System.out.println("我是胡歌代理2，找胡歌唱歌找我");
//            return method.invoke(hg, args);
//        }
//        if(method.getName().equals("act")){
//            System.out.println("我是胡歌代理2，找胡歌演电视剧找我");
//            return method.invoke(hg, args);
//        }
//
//        return null;
//    }
//
//
//
//}