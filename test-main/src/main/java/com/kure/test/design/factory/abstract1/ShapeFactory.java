package com.kure.test.design.factory.abstract1;

import com.kure.test.design.factory.base.Circle;
import com.kure.test.design.factory.base.Shape;
import com.kure.test.design.factory.base.Square;

/**
 * @author Lenovo
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Shape getShape(String type) {
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
