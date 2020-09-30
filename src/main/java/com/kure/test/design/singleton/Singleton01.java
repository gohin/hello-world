package com.kure.test.design.singleton;

/**
 * 饿汉模式
 * 1.私有构造函数，防止别人实例化
 * 2.静态属性，指向一个实例化对象
 * 3.公共方法，以便别人获取到实例化对象属性
 *
 * 线程安全 但浪费资源 SINGLETON = new Singleton();不适用也会初始化
 */
public class Singleton01 {
    private static final Singleton01 SINGLETON = new Singleton01();
    private Singleton01(){}
    public static Singleton01 getSingleton(){
        return SINGLETON;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Singleton01.getSingleton().hashCode());
            }).start();
        }
    }
}
