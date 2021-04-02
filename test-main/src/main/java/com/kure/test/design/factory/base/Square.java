package com.kure.test.design.factory.base;

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
