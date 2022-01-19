package com.jimmy.androidproject.studyfanxing;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2021/5/21
 * @Version: 1.0
 */
public class InfoImpl implements Info<String> {
    private String var;

    public InfoImpl(String var) {
        this.setVar(var);
    }

    @Override
    public void setVar(String x) {
        this.var = x;
    }

    @Override
    public String getVar() {
        return var;
    }
}
