package com.dongguo.threadsafe;


import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author Dongguo
 * @date 2021/8/24 0024-10:28
 * @description: Set集合线程不安全演示
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, "t" + i).start();
        }
    }
}