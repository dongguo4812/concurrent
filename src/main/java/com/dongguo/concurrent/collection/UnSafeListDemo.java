package com.dongguo.concurrent.collection;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @author Dongguo
 * @date 2021/9/3 0003-15:25
 * @description: Vector
 */
public class UnSafeListDemo {
    public static void main(String[] args) {
        List list = new Vector();
        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));//执行写操作
                System.out.println(list);//执行读操作
            },"t"+i).start();
        }
    }
}