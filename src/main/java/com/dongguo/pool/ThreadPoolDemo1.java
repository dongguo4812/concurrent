package com.dongguo.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-21:37
 * @description:
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //3个窗口
        // ExecutorService pool1 = Executors.newFixedThreadPool(3);
        //1个窗口
//        ExecutorService pool2 = Executors.newSingleThreadExecutor();
        //可扩容的窗口
        ExecutorService pool3 = Executors.newCachedThreadPool();
        //10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                pool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool3.shutdown();
        }
    }
}