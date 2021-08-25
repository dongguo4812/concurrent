package com.dongguo.sync.saleticket1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/8/23 0023-19:19
 * @description: 使用synchronized
 */
public class Ticket {
    private int number = 100;
    //创建可重入锁ReentrantLock
    private final ReentrantLock lock = new ReentrantLock(true);

    public void saleTicket() {
        //上锁
        lock.lock();
        if (number > 0) {
            number--;
            System.out.println(Thread.currentThread().getName() + "卖票成功，还剩:" + number + "张");
        }
        //解锁
        lock.unlock();
    }
}