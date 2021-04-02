package com.kure.test.util;

import java.io.Serializable;

/**
 * @author TANGYE
 * @version 1.0
 * @ClassName PkgCloneObj
 * @Description 深拷贝辅助类
 * @since 2018/6/17 14:05
 **/
public class PkgCloneObj<T> implements Serializable {

    private static final long serialVersionUID = 5522813359943250292L;

    T t;
    public T getT() {
        return t;
    }
    public void setT(T t) {
        this.t = t;
    }
}
