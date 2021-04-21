package com.kure.test.design.factory.normal;

/**
 * @author Lenovo
 */
public class TeslaFactory extends IFactory {
    @Override
    public Car createCar() {
        return new Tesla();
    }
}
