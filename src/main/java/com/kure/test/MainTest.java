package com.kure.test;

public class MainTest {

    public static void main(String[] args) {
//        i++;
//        System.out.println(i);
//        System.out.println(i++);

        boolean flag = true;
        Integer i = null;
        int j = 1;
        int k = flag ? i : j;
        System.out.println(k);

        MainTest mainTest = new MainTest();
        mainTest.add(i);
        System.out.println(i);
    }
    public void add(int i){
        i++;
        System.out.println("inner="+i);
    }

}
