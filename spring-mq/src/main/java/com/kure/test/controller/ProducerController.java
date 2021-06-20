package com.kure.test.controller;

import com.kure.test.jms.PayProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class ProducerController {

    @Autowired
    private PayProducer payProducer;

    private static final String MQ_TOPIC = "pay_test_topic";

    @Transactional
    public Object callback(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(MQ_TOPIC, "tag", "message".getBytes());

        SendResult sendResult = payProducer.getroducer().send(message);

        System.out.println(sendResult);

        return new HashMap<>();
    }



}
