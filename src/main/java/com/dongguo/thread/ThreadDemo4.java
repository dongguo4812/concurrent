package com.dongguo.thread;

import java.util.concurrent.*;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo4 {
    public static void main(String[] args)  {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run1");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        System.out.println(Thread.currentThread().getName() + " run2");
    }
}