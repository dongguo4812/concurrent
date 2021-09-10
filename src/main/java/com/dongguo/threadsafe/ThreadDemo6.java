package com.dongguo.threadsafe;


import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;



/**
 * @author Dongguo
 * @date 2021/8/24 0024-10:43
 * @description: Map集合线程安全演示
 */
public class ThreadDemo6 {
    public static void main(String[] args) {
        Map<String,String> map = new Hashtable<>();

        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                map.put(key,UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, "t" + i).start();
        }
    }
}