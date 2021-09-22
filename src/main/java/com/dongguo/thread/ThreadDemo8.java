package com.dongguo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo8 {
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) {
        try {
            method1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void method1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        });
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2 = 20;
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t2.join();
        t1.join();
        long end = System.currentTimeMillis();
        System.out.println("r1:" + r1 + "  r2:" + r2);
        System.out.println("总共花费" + (end - start));
    }
}