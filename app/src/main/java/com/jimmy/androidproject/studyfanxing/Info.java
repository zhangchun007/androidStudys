package com.jimmy.androidproject.studyfanxing;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2021/5/21
 * @Version: 1.0
 */
interface Info<T> {
    public void setVar(T x);

    public T getVar();// 定义抽象方法，抽象方法的返回值就是泛型类型
}
