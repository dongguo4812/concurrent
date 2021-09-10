package com.dongguo.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Dongguo
 * @date 2021/9/6 0006-20:49
 * @description:
 */
public class LockSupportDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println( "中断标志位1：" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println( "中断标志位2：" + Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName()+"被唤醒"+ Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getName() + "中断标志位3：" + t1.isInterrupted());
    }
}