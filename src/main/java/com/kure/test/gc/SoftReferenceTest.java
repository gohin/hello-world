package com.kure.test.gc;

import java.lang.ref.SoftReference;

/**
 * 强 弱 虚
 * jvm 参数 堆最大20M  -Xmx20M
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);

        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

//        byte[] b = new byte[1024*1024*12];

        SoftReference<byte[]> b = new SoftReference<>(new byte[1024*1024*12]);

        System.out.println(m.get());
    }
}
