package com.kure.test;

public class MainTest {

    private static int a  = 3;

    public MainTest() {
        System.out.println("11111");
    }

    static {
        System.out.println("static {}");
    }

    {
        System.out.println("{}");
    }

    public static void main(String[] args) {
        System.out.println(MainTest.a);
    }

}


//    public static void main(String[] args) {
//
//
//
//    int i = 0;
//        int j = 0;
//        j++;
//        System.out.println(i++);
//        System.out.println(i);
////        A a = new B();
////        a.sayHi();
//        {
//            int a = 0;
//        }
//
//        {
//            int a = 0;
//        }
//    }
//
//}
//
//class A{
//    public void sayHi(){
//        System.out.println("sayHi A");
//    }
//}
//
//class B extends A{
//    public void sayHello(){
//        System.out.println("sayHello");
//    }
//
//    public void sayHi(){
//        System.out.println("sayHi B");
//    }
//}
