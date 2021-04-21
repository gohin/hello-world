package com.kure.test.design.factory.normal;

/**
 * @author Lenovo
 */
public class BenzFactory extends CarFactory {

    @Override
    public Car createCar() {
        return new Benz();
    }
}
