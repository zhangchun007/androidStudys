package com.jimmy.androidproject.patternDesign.single;

/**
 * @Description: 恶汉式--线程安全
 * @Author: zhangchun
 * @CreateDate: 2022/2/11
 * @Version: 1.0
 */
class SingleTon1 {
    private SingleTon1(){}

    private static SingleTon1 mInstance=new SingleTon1();

    public static SingleTon1 getInstance(){
        return mInstance;
    }
}
