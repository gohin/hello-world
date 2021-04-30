package com.kure.test.design.factory.normal;

public class IFactoryTest {

    public static void main(String[] args) {
        IFactory carFactory = new BenzFactory();
        Car benz = carFactory.createCar();
        benz.drive();

        IFactory teslaFactory = new TeslaFactory();
        Car tesla = teslaFactory.createCar();
        tesla.drive();
    }
}
