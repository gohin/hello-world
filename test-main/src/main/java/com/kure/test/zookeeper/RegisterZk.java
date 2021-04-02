package com.kure.test.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 * ZooInsector 可视化工具
 */
public class RegisterZk {
    public static void main(String[] args) throws Exception {
        // 创建客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.2.129:2181")
                .sessionTimeoutMs(5000) // 会话超时时间
                .connectionTimeoutMs(5000) // 连接超时时间
                .retryPolicy(new ExponentialBackoffRetry(1000, 5)) //重连策略
                .namespace("base") // 命名控件
                .build();
        client.start();
        client.blockUntilConnected();
        // 注册服务
        ServiceInstance<Object> instance = ServiceInstance.builder().address("127.0.0.1").port(8099).name("service").build();
        ServiceDiscovery serviceDiscovery =
                ServiceDiscoveryBuilder.builder(Object.class).client(client).basePath("/com/kure/test").build();
        serviceDiscovery.registerService(instance);// 注册服务
        serviceDiscovery.start(); //启动服务

    }
}
