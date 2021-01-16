package com.kure.test.innerclass;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class InnerTest {

    public static void main(String[] args) throws Exception{
//        Cal.getMaxValue(3, 4);
//        InnerTest.Maths maths = new InnerTest().new Maths();
//        maths.getMaxValue(3, 5);
//
//        Map map = new HashMap<>(8);
//        Field field1 = map.getClass().getDeclaredField("threshold");
//        Field field2 = map.getClass().getDeclaredField("loadFactor");
//        field1.setAccessible(true);
//        field2.setAccessible(true);
//        System.out.println(field1.get(map));
//        System.out.println(field2.get(map));

        System.out.print(tableSizeFor(0));
    }

    /**
     * tableSizeFor的作用是返回一个大于输入参数且最小的为2的n次幂的数
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1; // 0000 0111
        n |= n >>> 1;  // 0000 0011 | 0000 0111 -> 0000 0111
        n |= n >>> 2;  // 0000 0001 | 0000 0111 -> 0000 0111
        n |= n >>> 4;  // 0000 0000 | 0000 0111 -> 0000 0111
        n |= n >>> 8;  // 0000 0000 | 0000 0111 -> 0000 0111
        n |= n >>> 16; // 0000 0000 | 0000 0111 -> 0000 0111
        return (n < 0) ? 1 : (n >= 10000) ? 10000 : n + 1;
    }

    /**
     * 静态内部类
     * 与外部InnerTest无关联
     */
    public static class Cal{
        static int getMaxValue(int a, int b) {
            return Math.max(a, b);
        }
    }

    public class Maths{
        public int getMaxValue(int a, int b) {
            return Math.max(a, b);
        }
    }
}
