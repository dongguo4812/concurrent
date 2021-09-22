package com.dongguo.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-14:26
 * @description: 同步器类 独占锁
 */
public class MySync extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        //初始state值为0
        if (compareAndSetState(0, 1)) {
            //加锁成功,并且设置锁的持有者Owner为当前线程
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {

        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    /**
     * 是否持有独占锁
     *
     * @return
     */
    @Override
    protected boolean isHeldExclusively() {
        return getState() == 1;
    }

    public Condition newCondition() {
        return new ConditionObject();
    }
}