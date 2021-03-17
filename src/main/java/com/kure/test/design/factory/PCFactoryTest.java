package com.kure.test.design.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 形容词用接口
 * 名词用抽象类
 */
public class PCFactoryTest {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            products.add(new Product());
        }
        ProductFactory factory = new ProductFactory(products);
        new Thread(new Consumer(factory)).start();

        new Thread(new Producer(factory)).start();
    }
}
