package com.kure.test.design.factory;

public class FactoryPattern {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactoryA();
        Product product = factory.createProduct();
        product.use();
    }
}

interface Product {
    void use();
}

//具体产品A：实现抽象产品中的抽象方法
class ConcreteProductA implements Product {
    @Override
    public void use() {
        System.out.println("具体产品A显示...");
    }
}

//具体产品B：实现抽象产品中的抽象方法
class ConcreteProductB implements Product {
    @Override
    public void use() {
        System.out.println("具体产品B显示...");
    }
}

//抽象工厂：提供了厂品的生成方法
interface Factory {
    Product createProduct();
}

//具体工厂A：实现了厂品的生成方法
class ConcreteFactoryA implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂A生成-->具体产品A.");
        return new ConcreteProductA();
    }
}

//具体工厂B：实现了厂品的生成方法
class ConcreteFactoryB implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂B生成-->具体产品B.");
        return new ConcreteProductB();
    }
}

