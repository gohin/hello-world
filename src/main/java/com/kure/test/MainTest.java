package com.kure.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    int i = 1;
    public static void main(String[] args) {
//        boolean flag = true;
//        Integer i = null;
//        int j = 1;
//        int k = flag ? i : j;
//        System.out.println(k);
//        MainTest mainTest = new MainTest();
//        mainTest.i++;

        Map<String, Object> map = new HashMap();
        List list =  (List) map.get("hello");
    }

}
