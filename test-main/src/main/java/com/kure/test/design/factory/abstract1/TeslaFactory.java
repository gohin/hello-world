package com.kure.test.design.factory.abstract1;

public class TeslaFactory extends AbstractFactory{
    @Override
    public Car createCar() {
        return new Tesla();
    }

    @Override
    public Ship createShip() {
        return new TeslaBoat();
    }

    @Override
    public Plane createPlane() {
        return new Boeing747();
    }
}
