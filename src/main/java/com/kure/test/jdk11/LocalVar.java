package com.kure.test.jdk11;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lenovo
 */
public class LocalVar {
    public static void main(String[] args) {
        /**
         * 变量推断
         */
        Arrays.asList("Java", "Python", "Ruby")
                .forEach((s) -> {
                    System.out.println("Hello, " + s);
                });
        Arrays.asList("Java", "Python", "Ruby")
                .forEach((var s) -> {
                    System.out.println("Hello, " + s);
                });

        List<Car> cars = Arrays.asList(new Car("Java"), new Car("Python"), new Car("Ruby"));
        cars.forEach((s) -> {
                    System.out.println("lambda Hello, " + s.getName());
                });

        for(var car : cars) {
            System.out.println("for Hello, " + car.getName());
        }
    }
}

class Car{
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
