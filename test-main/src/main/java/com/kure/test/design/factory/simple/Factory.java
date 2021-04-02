package com.kure.test.design.factory.simple;

import com.kure.test.design.factory.base.Circle;
import com.kure.test.design.factory.base.Square;
import com.kure.test.design.factory.base.Shape;

/**
 * 任何可以产生对象的方法和类，都可以称为工厂
 * 单例也是一种工厂 静态工厂
 *
 * 可扩展性不好
 * @author Lenovo
 */
public class Factory {

    public Shape getCircle() {
        // xx
        return new Circle();
    }
    public Shape getSquare() {
        // xx
        return new Square();
    }


    public static Shape getShape(String type) {
        Shape shape = null;
        switch (type) {
            case "circle":
                shape = new Circle();
                break;
            case "square":
                shape = new Square();
                break;
            default:
                shape = null;
        }
        return shape;
    }
}
