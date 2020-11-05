package com.kure.test.maths;

/**
 * 与运算（&）同为1时为1，否则为0
 * 或运算（|）同为0时为0，否则为1
 * 异或运算（^） 相同为0 不同为1
 */
public class MathsTest {

    public static void main(String[] args) {
        // 与（&）同为1时为1，否则为0
        System.out.println(0 & 1); // 0
        System.out.println(0 & 0); // 0
        System.out.println(1 & 1); // 1

        // 或（|）同为0时为0，否则为1
        System.out.println(0 | 1); // 1
        System.out.println(0 | 0); // 0
        System.out.println(1 | 1); // 1

        // 异或 相同为0 不同为1
        System.out.println(0 ^ 1); // 1
        System.out.println(0 ^ 0); // 0
        System.out.println(1 ^ 1); // 0
    }

}
