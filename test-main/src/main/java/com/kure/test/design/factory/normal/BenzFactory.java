package com.kure.test.design.factory.normal;

/**
 * @author Lenovo
 */
public class BenzFactory extends IFactory {

    @Override
    public Car createCar() {
        return new Benz();
    }
}
