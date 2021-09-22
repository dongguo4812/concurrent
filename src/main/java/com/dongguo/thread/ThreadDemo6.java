package com.dongguo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo6 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                System.out.println(Thread.currentThread().getName() + "----" + count++);
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
//                Thread.yield();
                System.out.println(Thread.currentThread().getName() + "----" + count++);
            }
        }, "t2");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}