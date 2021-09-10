package com.dongguo.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-13:58
 * @description:
 */
public class SyncLockDemo1 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "-外层");

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "-中层");

                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + "-内层");
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    //lock.unlock(); //这里故意注释，实现加锁次数和释放次数不一样
                    //由于加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直在等待。
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t2获得lock锁");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}