package com.dongguo.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/9/3 0003-14:36
 * @description: Lock实现生产者消费者
 */
public class ThreadLockUnlockDemo {
    public static void main(String[] args) {
        Number number = new Number();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    //为了体现出虚假唤醒问题
                    Thread.sleep(200);
                    number.incry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    number.decry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(400);
                    number.incry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    number.decry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4").start();
    }
}

class Number {
    private int count;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incry() throws InterruptedException {
        try {
            lock.lock();
            while (count != 0) {
                condition.await();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + "加一成功，count值为：" + count);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void decry() throws InterruptedException {
        try {
            lock.lock();
            while (count == 0) {
                condition.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName() + "减一成功，count值为：" + count);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}