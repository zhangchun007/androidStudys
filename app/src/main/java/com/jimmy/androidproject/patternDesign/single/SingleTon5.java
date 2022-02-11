package com.jimmy.androidproject.patternDesign.single;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:使用容器实现单利模式
 * @Author: zhangchun
 * @CreateDate: 2022/2/11
 * @Version: 1.0
 */
class SingleTon5 {
    private static Map<String, Object> objectMap = new HashMap<>();

    private SingleTon5() {
    }

    public static void registerService(String key, Object instance) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return objectMap.get(key);
    }
}
