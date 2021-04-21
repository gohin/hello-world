package com.kure.test.design.factory.demo;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory bydFactory = new BydFactory();

        Car byd = bydFactory.createCar();
        byd.drive();

        Plane boeing777 = bydFactory.createPlane();
        boeing777.fly();

        Ship bydBoat = bydFactory.createShip();
        bydBoat.go();

        AbstractFactory teslaFactory = new TeslaFactory();

        Car tesla = teslaFactory.createCar();
        tesla.drive();

        Plane boeing747 = teslaFactory.createPlane();
        boeing747.fly();

        Ship teslaBoat = teslaFactory.createShip();
        teslaBoat.go();
    }
}
