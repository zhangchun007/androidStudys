package com.jimmy.androidproject.studyfanxing;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2021/5/21
 * @Version: 1.0
 */
public class InfoImpls<T> implements Info<T> {
    private T var;

    public InfoImpls(T var) {
        this.setVar(var); // 通过构造方法设置属性内容
    }

    @Override
    public void setVar(T x) {
    }

    @Override
    public T getVar() {
        return var;
    }
}
