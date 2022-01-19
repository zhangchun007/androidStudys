package com.jimmy.androidproject.studyfanxing;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2021/5/21
 * @Version: 1.0
 */
public class MorePoint<T,U> {
    private T x;
    private T y;

    private U name;

    public void setX(T x) {
        this.x = x;
    }
    public T getX() {
        return this.x;

    }
    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public void setName(U name){
        this.name = name;
    }

    public U getName() {
        return this.name;
    }

}
