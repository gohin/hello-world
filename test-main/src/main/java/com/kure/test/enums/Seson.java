package com.kure.test.enums;

public enum Seson implements EnumInterface{
    SPRING{
        @Override
        public void sayHello() {
            System.out.println("hello spring");
        }

        @Override
        void sayHi() {
            System.out.println("spring");
        }
    },SUMMER{
        @Override
        public void sayHello() {
            System.out.println("hello summer");
        }
        @Override
        void sayHi() {
            System.out.println("summer");
        }
    },AUTOMER{
        @Override
        public void sayHello() {
            System.out.println("hello automer");
        }
        @Override
        void sayHi() {
            System.out.println("automer");
        }
    },WINTER{
        @Override
        public void sayHello() {
            System.out.println("hello winter");
        }
        @Override
        void sayHi() {
            System.out.println("winter");
        }
    };

    private Seson(){

    }

    abstract void sayHi();

    public static void main(String[] args) {
        Seson.SPRING.sayHi();
        Seson.SPRING.sayHello();
    }

    protected enum Car{
        BENZ("BENZ"),BMW("BMW"),AUDI("AUDI");
        private String name;
        private Car(String name){
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
