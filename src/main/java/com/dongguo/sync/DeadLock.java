package com.dongguo.sync;

import jdk.internal.dynalink.beans.StaticClass;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-14:25
 * @description: 模拟死锁
 */
public class DeadLock {
    static Object obj1 = new Object();
    static Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + "持有锁1，试图获取锁2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println(Thread.currentThread().getName() + "持有锁1，获取锁2成功");
                }
            }
        }, "ThreadA").start();
        new Thread(() -> {
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + "持有锁2，试图获取锁1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName() + "持有锁2，获取锁1成功");
                }
            }
        }, "ThreadB").start();
    }
}