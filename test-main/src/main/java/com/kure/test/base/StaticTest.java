package com.kure.test.base;

/**
 * @author Lenovo
 */
public class StaticTest {

    //
    static int a = 1;

    static {
        // 静态变量前置 能赋值 能访问
        a = 3;
        // 静态变量后置 能赋值 但是不能访问
        b = 3;
    }

    static int b = 1;

    public static void main(String[] args) {
        // 结果3
        System.out.println(StaticTest.a);
        // 结果1
        System.out.println(StaticTest.b);
    }
}
