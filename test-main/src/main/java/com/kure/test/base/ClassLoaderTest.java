package com.kure.test.base;

import java.io.IOException;

/**
 * 双亲委派
 * bootstrap -> ext classloader(<=8) platformClassLoader(>8) -> app classloader
 *
 * jdk类加载器 ClassLoader   SecureClassLoader  UrlClassLoader
 *
 */
public class ClassLoaderTest extends ClassLoader {

    public static void main(String[] args) throws IOException {
        java.lang.ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println("classLoader="+classLoader);


        System.out.println("classLoader.getParent="+classLoader.getParent());

        System.out.println("classLoader.getGrandParent="+classLoader.getParent().getParent());


        System.out.println("bootstrap classLoader dir="+System.getProperty("sun.boot.class.path"));
        System.out.println("extension classLoader dir="+System.getProperty("java.ext.dir"));
        System.out.println("application classLoader dir="+System.getProperty("java.class.path"));

        System.getProperties().forEach((key, value)->{
            System.out.println(key +"="+value);
        });

    }
}
