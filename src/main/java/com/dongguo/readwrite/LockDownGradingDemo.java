package com.dongguo.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dongguo
 * @date 2021/9/9 0009-20:21
 * @description: 锁降级：遵循获取写锁→再获取读锁→再释放写锁的次序，写锁能够降级成为读锁。
 * <p>
 * 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁。
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        readLock.lock();
        System.out.println("-------正在读取");

        System.out.println("-------读完了");
        readLock.unlock();

        writeLock.lock();
        System.out.println("-------正在写入");

        System.out.println("-------写完了");
        writeLock.unlock();
    }
}