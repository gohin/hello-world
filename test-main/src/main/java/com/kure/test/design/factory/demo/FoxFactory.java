package com.kure.test.design.factory.demo;

public class FoxFactory extends AbstractFactory{
    @Override
    public Car createCar() {
        return new Tesla();
    }

    @Override
    public Ship createShip() {
        return new Boat();
    }

    @Override
    public Plane createPlane() {
        return new Boeing747();
    }
}
