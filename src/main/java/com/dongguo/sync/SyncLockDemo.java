package com.dongguo.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-13:58
 * @description:
 */
public class SyncLockDemo {
    Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        new SyncLockDemo().add();
    }
    public long number = 0;

    //递归
    public void add(){
        try {
            lock.lock();
            number++;
            System.out.println(number);
            add();//自己调自己
        } finally {
            lock.unlock();
        }
    }
}