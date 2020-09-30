package com.kure.test.design.factory;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * wait  notify 要同一个对象
 */
public class ProductFactory {

    List<Product> products  = null;

    private Object object = new Object();

    BlockingQueue blockingQueue = new ArrayBlockingQueue(10);

    public ProductFactory(List<Product> products) {
        this.products = products;
    }

    public void produce() {
        while(true) {
            try {
                synchronized (object) {
                    while (products.size() == 10) {
                        System.out.println("products.wait=" + products.size());
                        object.wait();
                    }
                    products.add(new Product());
                    System.out.println("products.size=" + products.size());
                    object.notify();
                }
            } catch (Exception e) {

            }
        }
    }

        public void consume()  {
        while(true) {
            try {
                synchronized (object) {
                    while (products.size() <= 0) {
                        System.out.println("consume.wait=" + products.size());
                        object.wait();
                    }
                    products.remove(products.size()-1);
                    System.out.println("consume.size=" + products.size());
                    object.notify();
                }
            } catch (InterruptedException e) {

            }

        }
    }

//    public void produce() {
//        while (true) {
//            try {
//                blockingQueue.put(new Product());
//                System.out.println("produce Product=" + blockingQueue.size());
//            } catch (InterruptedException e) {
//
//            }
//        }
//    }
//
//    public void consume() {
//        while (true) {
//            try {
//                blockingQueue.take();
//                System.out.println("consume take=" + blockingQueue.size());
//            }catch (InterruptedException e) {
//
//            }
//
//        }
//    }


}
