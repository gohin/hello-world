package com.kure.test.design.factory;

import java.util.List;

/**
 * wait  notify 要同一个对象
 */
public class ProductFactory {

    List<Product> products  = null;

    public ProductFactory(List<Product> products) {
        this.products = products;
    }

    public void produce() {
        while(true) {
            try {
                synchronized (ProductFactory.class) {
                    while (products.size() == 10) {
                        System.out.println("products.wait=" + products.size());
                        Thread.sleep(1000);
                        ProductFactory.class.wait();
                    }
                    products.add(new Product());
                    System.out.println("products.size=" + products.size());
                    Thread.sleep(1000);
                    ProductFactory.class.notify();
                }
            } catch (Exception e) {

            }
        }
    }

    public void consume()  {
        while(true) {
            try {
                synchronized (ProductFactory.class) {
                    while (products.size() <= 0) {
                        System.out.println("consume.wait=" + products.size());
                        Thread.sleep(1000);
                        ProductFactory.class.wait();
                    }
                    products.remove(products.size()-1);
                    System.out.println("consume.size=" + products.size());
                    Thread.sleep(1000);
                    ProductFactory.class.notify();
                }
            } catch (InterruptedException e) {

            }

        }
    }

}
