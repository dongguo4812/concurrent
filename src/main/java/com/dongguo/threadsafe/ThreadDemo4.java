package com.dongguo.threadsafe;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author Dongguo
 * @date 2021/8/24 0024-10:28
 * @description: Set集合线程安全演示
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, "t" + i).start();
        }
    }
}