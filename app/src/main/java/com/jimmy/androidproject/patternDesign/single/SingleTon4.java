package com.jimmy.androidproject.patternDesign.single;

/**
 * @Description: 静态内部类--线程安全
 * @Author: zhangchun
 * @CreateDate: 2022/2/11
 * @Version: 1.0
 */
class SingleTon4 {
    private SingleTon4() {
    }

    private static SingleTon4 getInstance() {
        return SingletonHolder.mInstance;
    }

    public static class SingletonHolder {
        private static final SingleTon4 mInstance = new SingleTon4();
    }

}
