package com.dongguo.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-13:58
 * @description: 死锁产生
 */
public class SyncLockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "-外层");

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "-内层");
                } finally {
                    //不去释放锁
                //    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "ThreadA").start();
        new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "-执行");
            lock.unlock();
        },"ThreadB").start();
    }
}