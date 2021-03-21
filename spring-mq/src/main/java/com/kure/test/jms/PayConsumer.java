package com.kure.test.jms;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

@Component
public class PayConsumer {
    private DefaultMQPushConsumer consumer;

    private String consumerGroup = "pay_consumer_group";

    public PayConsumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);

        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.subscribe(JmsConfig.MQ_TOPIC, "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context)->{
            try {
                Message message = msgs.get(0);
                System.out.println(message.toString());
                String topic = message.getTopic();
                String body = new String(message.getBody(), "utf-8");
                String tags = message.getTags();
                String keys = message.getKeys();
                System.out.println(topic+body+tags+keys);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        consumer.start();
    }
}
