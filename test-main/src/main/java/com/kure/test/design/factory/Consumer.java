package com.kure.test.design.factory;

/**
 * 消费者
 */
public class Consumer implements Runnable{
    ProductFactory productFactory;
    public Consumer(ProductFactory productFactory){
        this.productFactory = productFactory;
    }

    public void consume(){
        productFactory.consume();
    }

    @Override
    public void run() {
        this.consume();
    }
}
