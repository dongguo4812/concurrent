package com.dongguo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo5 {
    public static void main(String[] args)  {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        System.out.println(t1.getState());//获取t1线程的状态
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " run2");
        System.out.println(t1.getState());//获取t1线程的状态
        t1.interrupt();//打断正在睡眠的线程
        System.out.println(t1.getState());//获取t1线程的状态
    }
}