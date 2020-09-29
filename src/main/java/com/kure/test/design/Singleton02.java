package com.kure.test.design;

/**
 * 懒汉模式
 * 1.私有构造函数，防止别人实例化
 * 2.静态属性，指向一个实例化对象
 * 3.公共方法，以便别人获取到实例化对象属性
 */
public class Singleton02 {
    private static Singleton02 SINGLETON = null;
    private Singleton02(){}

    /**
     * 线程不安全
     * @return
     */
//    public static Singleton01 getSingleton(){
//        if (SINGLETON == null) {
//            SINGLETON = new Singleton01();
//        }
//        return SINGLETON;
//    }

    /**
     * 锁的颗粒度大
     * @return
     */
//    public synchronized static Singleton01 getSingleton(){
//        if (SINGLETON == null) {
//            SINGLETON = new Singleton01();
//        }
//        return SINGLETON;
//    }

    /**
     * 双重校验锁
     * 优点：节约资源（只有需要该对象的时候才会实例化）
     * 缺点：写法复杂，耗性能（还是上了锁，还是耗性能）
     * @return
     */
    public static Singleton02 getSingleton(){
        if (SINGLETON == null) {
            synchronized(Singleton02.class) {
                if (SINGLETON == null) {
                    SINGLETON = new Singleton02();
                }
            }
        }
        return SINGLETON;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Singleton02.getSingleton().hashCode());
            }).start();
        }
    }
}
