package com.jimmy.androidproject.patternDesign.single;

/**
 * @Description: dcl--线程安全
 * @Author: zhangchun
 * @CreateDate: 2022/2/11
 * @Version: 1.0
 */
class SingleTon3 {
    private SingleTon3() {
    }

    private volatile static SingleTon3 mInstance;

    public static synchronized SingleTon3 getInstance() {
        if (mInstance == null) {
            synchronized (SingleTon3.class) {
                if (mInstance == null) {
                    mInstance = new SingleTon3();
                }
            }
        }
        return mInstance;
    }
}
