package com.jimmy.androidproject.patternDesign.single;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:枚举
 * @Author: zhangchun
 * @CreateDate: 2022/2/11
 * @Version: 1.0
 */
public enum SingleTon6 {
    INSTANCE;

    public void doSomething() {
        System.out.println("do sth...");
    }
}
