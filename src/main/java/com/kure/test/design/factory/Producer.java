package com.kure.test.design.factory;

/**
 * 生产者
 */
public class Producer implements Runnable{

    ProductFactory productFactory;
    public Producer(ProductFactory productFactory){
        this.productFactory = productFactory;
    }

    public void produce(){
        productFactory.produce();
    }

    @Override
    public void run() {
        this.produce();
    }
}
