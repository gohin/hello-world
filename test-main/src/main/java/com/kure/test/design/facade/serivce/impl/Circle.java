package com.kure.test.design.facade.serivce.impl;

import com.kure.test.design.facade.serivce.Shape;

/**
 * @author Lenovo
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
