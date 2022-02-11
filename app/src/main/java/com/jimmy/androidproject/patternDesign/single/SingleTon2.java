package com.jimmy.androidproject.patternDesign.single;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2022/2/11
 * @Version: 1.0
 */
class SingleTon2 {
    private SingleTon2(){}

    private static SingleTon2 mInstance;

    public static synchronized SingleTon2 getInstance(){
        if (mInstance==null){
            mInstance=new SingleTon2();
        }
        return mInstance;
    }
}
