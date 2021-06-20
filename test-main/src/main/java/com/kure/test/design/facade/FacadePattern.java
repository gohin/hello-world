package com.kure.test.design.facade;

/**
 * 外观模式（Facade Pattern）隐藏系统的复杂性，
 * 并向客户端提供了一个客户端可以访问系统的接口。这种类型的设计模式属于结构型模式，它向现有的系统添加一个接口，来隐藏系统的复杂性。
 * 这种模式涉及到一个单一的类，该类提供了客户端请求的简化方法和对现有系统类方法的委托调用。
 *
 *
 * 优点： 1、减少系统相互依赖。 2、提高灵活性。 3、提高了安全性。
 *
 * 缺点：不符合开闭原则，如果要改东西很麻烦，继承重写都不合适。
 *
 * 使用场景： 1、为复杂的模块或子系统提供外界访问的模块。 2、子系统相对独立。
 *
 * 注意事项：在层次化结构中，可以使用外观模式定义系统中每一层的入口。
 *
 */
public class FacadePattern {

    public static void main(String[] args) {
        new Facade().doSomething();
    }
}

class Client1{

    public static void main(String[] args) {
        new Client1().doSomething();
    }
    public void doSomething() {
        new SubSystem1().method1();
        new SubSystem2().method2();
        new SubSystem3().method3();
    }
}

class Client2{

    public static void main(String[] args) {
        new Client2().doSomething();
    }

    public void doSomething() {
        new SubSystem1().method1();
        new SubSystem2().method2();
        new SubSystem3().method3();
    }
}

class Facade{

    public void doSomething(){
        new SubSystem1().method1();
        new SubSystem2().method2();
        new SubSystem3().method3();
    }
}

class SubSystem1 {
    public void method1() {
        System.out.println("SubSystem1.method1 executed");
    }
}


class SubSystem2 {
    public void method2() {
        System.out.println("SubSystem2.method2 executed");
    }
}


class SubSystem3 {
    public void method3() {
        System.out.println("SubSystem3.method3 executed");
    }
}

