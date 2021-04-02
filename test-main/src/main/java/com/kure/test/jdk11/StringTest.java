package com.kure.test.jdk11;

public class StringTest {
    public static void main(String[] args) {

        String s = " Javastack ";
        // 判断字符串是否为空白 // true
        System.out.println(" ".isBlank());
        // 去除首尾空格 // "Javastack"
        System.out.println(s.strip());
        // 去除尾部空格 // " Javastack"
        System.out.println(s.stripTrailing());
        // 去除首部空格 // "Javastack "
        System.out.println(s.stripLeading());
        // 复制字符串 // "JavaJavaJava"
        System.out.println(s.repeat(3));
        // 行数统计 // 3
        System.out.println("A\nB\nC".lines().count());
    }
}
