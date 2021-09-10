package com.dongguo.concurrent.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/9/3 0003-10:14
 * @description: 实现3个售票员卖出100张票的案例
 * 使用Lock
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            //循环100次保证能够卖光票
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "T1").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "T2").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "T3").start();
    }
}

/**
 * @author Dongguo
 * @description: 资源类
 */
class Ticket {
    private int count = 100;
    //创建可重入锁ReentrantLock
    private Lock lock = new ReentrantLock(true);

    public void saleTicket() {
        //上锁
        lock.lock();
        try {
            if (count > 0) {
                count--;
                System.out.println(Thread.currentThread().getName() + "卖票成功，还剩" + count + "张票！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }
}