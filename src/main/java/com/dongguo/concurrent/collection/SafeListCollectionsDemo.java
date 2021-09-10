package com.dongguo.concurrent.collection;


import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Dongguo
 * @date 2021/9/3 0003-17:16
 * @description: CopyOnWriteArrayList
 */
public class SafeListCollectionsDemo {
    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "t" + i).start();
        }
    }
}