package com.kure.test.gc;

/**
 * VM参数： -verbose:gc -Xms20M -Xmx20M -Xmn10M(yong) -XX:PrintGCDetials -XX:SurvivorRatio=8
 * 新生代 老年代比例 –XX:NewRatio    默认1：2
 */
public class TestAllocation {

    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];

        allocation2 = new byte[2 * _1MB];

        allocation3 = new byte[2 * _1MB];

        allocation4 = new byte[4 * _1MB]; // 出现full gd
    }

    public static void main(String[] args) {
        TestAllocation.testAllocation();
    }
}
