package com.kure.test;

import com.erayt.webnet.client.WebNetClient;
import com.erayt.webnet.client.WebNetHandler;

public class WebnetTest {
    public static void main(String[] args) throws Exception {
        WebNetClient client = new WebNetClient();
        client.setUrl("ws://192.168.193.10:80/ws/SYSTEM/CIM000000121/CIM000000126/S");
        client.setHandler(new WebNetHandler() {
            @Override
            public void messageRead(WebNetClient client, String msg) {
                System.out.println("收到服务端消息=" + msg);
            }
            @Override
            public void loginOK(WebNetClient client) {
                System.out.println("loginOK");
            }
        });
        client.open();
        client.send("#A", "PSSSS1111");
        client.send("PSSSS1111", "hello world S");
        client.send("S", "hello world S");
        Thread.sleep(8000);
        client.close();

//        WebNetClient client = new WebNetClient();
//        client.setUrl("ws://192.168.193.10:80/ws/ch0/ch1/SYSTEM"); // ch0/ch1 表示注册的两个title， 采用"/" 间隔两title
//        client.setHandler(new WebNetHandler() {
//            @Override
//            public void messageRead(WebNetClient client, String msg) {
//                System.out.println("接收到信息。" + msg);
//            }
//            @Override
//            public void loginOK(WebNetClient webnet) {
//                System.out.println("登陆成功，动态添加title - ch0");
//            }
//        });
//        client.open();
//        client.send("#A", "CIM000000121");
//        client.send("CIM000000121", "hello --------");
//        Thread.sleep(5000);
//
//        client.close();

    }
}
