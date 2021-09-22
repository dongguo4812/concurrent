package com.dongguo.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-14:59
 * @description:
 */
public class LockInterruptiblyDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("等待锁的过程中被打断");
                return;
            }
            try {
                System.out.println(Thread.currentThread().getName()+"获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        System.out.println(Thread.currentThread().getName()+"获得了锁");

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"中断t1等待");
            t1.interrupt();//中断等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}