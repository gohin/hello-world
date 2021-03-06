package com.kure.test.design.singleton;

public class Singleton {
    public static void main(String[] args) throws Exception {

        System.out.println(Singleton05.INSTANCE1.sayHi());

        // 创建100个线程同时访问实例
        /*for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Singleton04.getInstance().hashCode());
            }).start();
        }

        // 反射破坏单例
        Class<Singleton04> clazz = Singleton04.class;
        // 拿到无参构造函数并将其设置为可访问，无视private
        Constructor<Singleton04> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        // 创建对象
        Singleton04 singleton04 = constructor.newInstance();
        System.out.println("反射：" + Singleton04.getInstance().hashCode());*/
    }
}

enum Singleton04{
    INSTANCE;
    public static Singleton04 getInstance() {
        return INSTANCE;
    }
}


enum Singleton05{
    INSTANCE1{
        @Override
        public int sayHi(){
            System.out.println("hi");
            sayHello();
            return 1;
        }
    };

    abstract int sayHi();

    void sayHello(){
        System.out.println("hello");
    }
}


