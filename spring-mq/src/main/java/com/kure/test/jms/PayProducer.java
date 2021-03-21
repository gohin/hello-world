package com.kure.test.jms;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

@Component
public class PayProducer {

    private String produceGroup = "pay_group";

    private String nameServerAddr = "ip:port";

    private DefaultMQProducer producer;

    public PayProducer(){
        producer = new DefaultMQProducer();

        producer.setNamesrvAddr(nameServerAddr);
        start();
    }


    public DefaultMQProducer getroducer(){
        return producer;
    }

    public void start(){
        try{
            this.producer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void stop(){
        producer.shutdown();
    }
}
