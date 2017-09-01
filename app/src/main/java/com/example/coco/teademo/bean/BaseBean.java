package com.example.coco.teademo.bean;

/**
 * Created by coco on 2017/8/28.
 */

public class BaseBean<T> {
    private int code;
    private T obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

}