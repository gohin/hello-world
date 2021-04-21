package com.kure.test.design.factory.demo;

/**
 * @author Lenovo
 */
public abstract class AbstractFactory {
    public abstract Car createCar();

    public abstract Ship createShip();

    public abstract Plane createPlane();
}
