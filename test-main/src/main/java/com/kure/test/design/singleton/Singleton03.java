package com.kure.test.design.singleton;

/**
 * 单例3 内部类
 * 写法简单、节约资源（只有调用了getINSTANCE()方法才会加载内部类，才会实例化对象）、
 * 线程安全（JVM保证了内部类的线程安全）
 * 缺点：会被序列化或者反射破坏单例
 */
public class Singleton03 {
    private Singleton03() {}
    private static class InnerClass {
        public static final Singleton03 INSTANCE = new Singleton03();
    }
    // 公共方法
    public static Singleton03 getSingleton() {
        return InnerClass.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Singleton03.getSingleton().hashCode());
            }).start();
        }
    }

}
