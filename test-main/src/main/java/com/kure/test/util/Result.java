package com.kure.test.util;

import java.io.Serializable;

public class Result implements Serializable {

    // 0成功，-1失败
    private int status;
    private String msg;
    private Object data;
    private String action;

    public static Result success() {
        return Result.success("操作成功", null);
    }

    public static Result success(Object data) {
        return Result.success("操作成功", data);
    }

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.status = 0;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.status = -1;
        result.data = null;
        result.msg = msg;
        return result;
    }

    public Result action(String action){
        this.action = action;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}