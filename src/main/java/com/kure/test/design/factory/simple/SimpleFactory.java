package com.kure.test.design.factory.simple;

import com.kure.test.design.factory.Shape;

/**
 * 工厂模式（Factory Pattern）是 Java 中最常用的设计模式之一。
 * 这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 *
 * 在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，并且是通过使用一个共同的接口来指向新创建的对象。
 *
 * 优点： 1、一个调用者想创建一个对象，只要知道其名称就可以了。
 * 2、扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。
 * 3、屏蔽产品的具体实现，调用者只关心产品的接口。
 *
 * 缺点：每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加，
 * 在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。
 */
public class SimpleFactory {

    public static void main(String[] args) {
        Shape shape = Factory.getShape("circle");
        shape.draw();
    }
}
