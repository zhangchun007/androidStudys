package com.jimmy.androidproject;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2022/2/13
 * @Version: 1.0
 */
public class LinkedHashMapTest {
    @Test
    public void testLinkedHashMap(){
        Map map = new LinkedHashMap<String, String>(10, 0.75f, true);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        map.get("2");//2移动到了内部的链表末尾
        map.get("4");//4调整至末尾
        map.put("3", "e");//3调整至末尾
        map.put(null, null);//插入两个新的节点 null
        map.put("5", null);//5

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
