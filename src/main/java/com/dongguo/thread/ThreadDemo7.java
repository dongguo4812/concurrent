package com.dongguo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo7 {
    static int r = 0;

    public static void main(String[] args) {
        try {
            method1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void method1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r = 10;
        }, "t1");
        t1.start();
        t1.join();
        System.out.println(Thread.currentThread().getName() + "结果为：" + r);
    }
}