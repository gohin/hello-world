package com.kure.test.design.factory.simple;

import com.kure.test.design.factory.Circle;
import com.kure.test.design.factory.Square;
import com.kure.test.design.factory.Shape;

/**
 * @author Lenovo
 */
public class Factory {

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
