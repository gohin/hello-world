package com.kure.test.collection;

import java.util.ArrayList;
import java.util.List;

public class TypeListTest {

    public <T> void sayHello(T t){
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
        TypeListTest typeListTest = new TypeListTest();

        List<String> list = new ArrayList<>(8);

        typeListTest.sayHello(list);
    }
}
