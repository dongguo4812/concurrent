package com.dongguo.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-15:42
 * @description:
 */
public class TimeOutLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                if (!lock.tryLock(1,TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + "获取不到等待1s后失败，返回");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + "获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}