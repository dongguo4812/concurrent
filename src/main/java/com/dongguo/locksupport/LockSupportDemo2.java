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
            for (int i = 0; i < 5; i++) {

                System.out.println( "park前中断标志位1：" + Thread.currentThread().isInterrupted());
                Thread.interrupted();//返回中断状态并清除中断状态
                LockSupport.park();
                System.out.println(Thread.currentThread().getName()+"被唤醒 中断状态2"+ Thread.currentThread().isInterrupted());
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();

    }
}