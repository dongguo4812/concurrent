package com.dongguo.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-14:15
 * @description:
 */
public class LiveLockDemo {
    static volatile int count = 10;

    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            // 期望减到 0 退出循环
            while (count > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println(Thread.currentThread().getName()+" count="+count);
            }
        }, "t1").start();
        new Thread(() -> {
            // 期望超过 20 退出循环
            while (count < 20) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println(Thread.currentThread().getName()+" count="+count);
            }
        }, "t2").start();
    }
}