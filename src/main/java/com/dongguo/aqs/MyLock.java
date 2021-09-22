package com.dongguo.aqs;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-14:27
 * @description: 自定义锁（不可重入锁）
 */
public class MyLock implements Lock {

    //独占锁
    private MySync sync = new MySync();

    /**
     * 加锁，失败进入等待
     */
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * 加锁，可打断
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * 尝试一次加锁
     * @return
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    /**
     * 尝试加锁带超时时间
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    /**
     * 创建条件变量
     * @return
     */
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}