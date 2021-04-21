package com.kure.test.design.factory.demo;

public class BydFactory extends AbstractFactory{
    @Override
    public Car createCar() {
        return new Byd();
    }

    @Override
    public Ship createShip() {
        return new BydBoat();
    }

    @Override
    public Plane createPlane() {
        return new Boeing777();
    }
}
