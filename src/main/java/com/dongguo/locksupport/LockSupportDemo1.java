package com.dongguo.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Dongguo
 * @date 2021/9/6 0006-16:14
 * @description:
 */
public class LockSupportDemo1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "--阻塞");
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "--被唤醒");
        }, "t1");
        t1.start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "--t2发出唤醒通知");
            LockSupport.unpark(t1);
        },"t2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "--t3发出唤醒通知");
            LockSupport.unpark(t1);
        },"t3").start();
    }
}