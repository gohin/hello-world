package com.kure.test.design.flyweight;

/**
 * 享元模式
 *
 * 主要用于减少创建对象的数量，以减少内存占用和提高性能。
 * 这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
 *
 * String
 *
 * -Djava.lang.Integer.IntegerCache.high=256
 */
public class FlyweightPattern {
    public static void main(String[] args) {
        Integer i1 = Integer.valueOf(123);
        Integer i2 = Integer.valueOf(123);

        Integer i3 = Integer.valueOf(223);
        Integer i4 = Integer.valueOf(223);

        System.out.println(i1 == i2);

        System.out.println(i3 == i4);
    }
}
