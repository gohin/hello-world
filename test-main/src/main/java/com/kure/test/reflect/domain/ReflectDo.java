package com.kure.test.reflect.domain;

import com.kure.test.reflect.MyAnno;

public class ReflectDo {

    @MyAnno(value = "kure")
    private String sex="ll";

    private static final String COMM=",";

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
