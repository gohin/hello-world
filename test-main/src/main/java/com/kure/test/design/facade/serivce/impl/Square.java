package com.kure.test.design.facade.serivce.impl;

import com.kure.test.design.facade.serivce.Shape;

/**
 * @author Lenovo
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
