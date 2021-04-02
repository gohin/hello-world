package com.kure.test.design.factory.demo;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory factory = new FoxFactory();

        Car bmw = factory.createCar();
        bmw.move();

        Plane boeing = factory.createPlane();
        boeing.fly();

        Ship boat = factory.createShip();
        boat.go();
    }
}
