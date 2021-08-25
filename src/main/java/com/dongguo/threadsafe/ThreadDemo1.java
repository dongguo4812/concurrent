package com.dongguo.threadsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author Dongguo
 * @date 2021/8/24 0024-9:50
 * @description: List集合线程不安全演示
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List list = new CopyOnWriteArrayList();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread" + i).start();
        }
    }
}