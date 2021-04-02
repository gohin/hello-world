package com.kure.test.gc;

/**
 * -verbose:gc -Xms10M -Xmx10M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:/logs/my-stack-dump.hprof
 */
public class StackOutOfMemoryTest {
    public static void main(String[] args) {
        StackOutOfMemoryTest test = new StackOutOfMemoryTest();
        test.test();
    }

    public void test(){
        test();
    }
}
