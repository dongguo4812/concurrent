package com.dongguo.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-20:10
 * @description:演示读写锁降级
 */
public class Demo1 {
    public static void main(String[] args) {
        //可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        //读锁
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        //写锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
        //获取读锁
        readLock.lock();
        System.out.println("我是读锁");
        //获取写锁
        writeLock.lock();
        System.out.println("我是写锁");

        //释放写锁
        writeLock.unlock();
        //释放读锁
        readLock.unlock();
    }
}