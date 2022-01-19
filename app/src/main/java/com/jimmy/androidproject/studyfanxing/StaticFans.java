package com.jimmy.androidproject.studyfanxing;

import android.util.Log;

import java.util.List;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2021/5/21
 * @Version: 1.0
 */
public class StaticFans {
    //静态函数
    public static <T> void StaticMethod(T a) {
        Log.d("harvic", "StaticMethod: " + a.toString());
    }

    //普通函数
    public <T> void OtherMethod(T a) {
        Log.d("harvic", "OtherMethod: " + a.toString());
    }

    //返回值中存在泛型
    public static <T> List<T> parseArray(String response, Class<T> object) {
        List<T> modelList = null;
//        modelList = JSONObject.parseArray(response, object);
        return modelList;
    }


    //泛型数组
    public static <T> T[] fun(T... args) {
        return args;
    }


    //泛型绑定（接口）
    public static <T extends Comparable> T min(T... a) {
        T smallest = a[0];
        for (T item : a) {
            if (smallest.compareTo(item)) {
                smallest = item;
            }
        }
        return smallest;
    }

    //泛型绑定（类）

    public static <T extends Fruit>String getFruitName(T t){
        return t.getName();
    }



}
