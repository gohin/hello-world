package com.kure.test.design.proxy.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MybatisProxyTest {

    public static void main(String[] args) {
        MybatisJdkProxy mybatisJdkProxy = new MybatisJdkProxy(new SqlSession(), UserMapperInterface.class);

        UserMapperInterface userMapperInterface = (UserMapperInterface)Proxy.newProxyInstance(mybatisJdkProxy.getClass().getClassLoader(), new Class[]{UserMapperInterface.class}, mybatisJdkProxy);

        userMapperInterface.getUserInfo();
    }
}
