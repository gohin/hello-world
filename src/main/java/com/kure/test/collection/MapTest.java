package com.kure.test.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(16);
        for (int i =0; i < 17; i++) {
            // resize test
            map.put(i+"", i+"");
        }

    }
}
