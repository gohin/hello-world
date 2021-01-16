package com.kure.test.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xms10M -Xmx10M -Xmn10M -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:/logs/my-heap-dump.hprof
 */
public class OutOfMemoryTest {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();

        while (true) {
            lists.add(new String());
        }
    }
}
