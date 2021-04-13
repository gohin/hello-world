package com.kure.test.base;

/**
 * loading 类加载 class加载到内存
 * linking verification preparation(静态变量赋值默认值) resolution(符号引用解析为直接引用) 静态解析
 * initializing 静态变量赋值初始值
 * gc
 */
public class ClassLoaderT {

    public static void main(String[] args) {
        System.out.println(T.count);
    }
}
class T{
//    public static T t = new T();  // 2
    public static int count = 2;
    public static T t = new T(); // 3

    // M->0 M->8
    private int m = 8;

    private T(){
        count ++;
        System.out.println(m);
    }
}
