package com.kure.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;

/**
 * rocketMq 4.X
 * connectionFactory ↓ 连接工厂
 * connection ↓ 连接器
 * session ↓ 会话连接
 * provider 消息生产者 → destination 发送目标地址
 * consumer 消息消费者 → destination 接受目标地址
 *
 * 1，支持broker Consumer端消息过滤
 * 2. 支持发布订阅 点对点
 * 3. 支持 pull and push模式
 * 4. 单一队列百万消息、亿级堆积
 * 5. master(N) salve(N)
 * 6. 分布式
 * 7. 消息失败重试、支持特定level的定时消息
 * 8。 采用netty
 * 9. 支持分布式事务
 * 10。适合金融类业务，高可用和审计功能
 *
 * producer producer group 生产者组
 * consumer consumer group 消费者组
 * tag 标签 子主题 对topic的进一步细化
 * topic 主题 某某消息 queue消息的物理单位 自动默认4个 手动8个
 * message 消息 指定一个topic
 * broker MQ程序 通道 10911
 * name Server 注册中心 比zookeeper轻量 9876
 * offset 偏移量
 * commit log 消息存储在commit log文件里面
 *
 * rocketmq 官网教程
 *   > unzip rocketmq-all-4.8.0-source-release.zip
 *   > cd rocketmq-all-4.8.0/
 *   > mvn -Prelease-all -DskipTests clean install -U
 *   > cd distribution/target/rocketmq-4.8.0/rocketmq-4.8.0
 *
 *   nohup sh bin/mqnamesrv & --- '&'守护进程
 */
@SpringBootConfiguration
public class MqStart {
    public static void main(String[] args) {
        SpringApplication.run(MqStart.class, args);
    }
}
