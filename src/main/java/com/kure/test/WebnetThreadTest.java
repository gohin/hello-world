package com.kure.test;

import ch.qos.logback.core.util.FileUtil;
import com.erayt.webnet.client.WebNetClient;
import com.erayt.webnet.client.WebNetHandler;

import java.util.stream.Stream;

public class WebnetThreadTest {
    public static void main(String[] args) throws Exception {
        WebNetClient client = new WebNetClient();
        client.setUrl("ws://192.168.193.10:80/ws/SYSTEM/CIM000000126_C/CIM000000121_C/P_XAUUSD/P_XAGUSD");
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
    }
}
