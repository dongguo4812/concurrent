package com.dongguo.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/9/3 0003-15:01
 * @description:
 */
public class ThreadOrderAccessDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.printA(i);
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.printB(i);
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.printC(i);
            }
        }, "C").start();
    }
}

class ShareResource {
    private int flag = 0;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(int i) {
        try {
            lock.lock();
            while (flag != 0) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出,第" + i + "轮开始");
            //打印两次
            for (int j = 0; j < 2; j++) {
                System.out.println("A");
            }
            //唤醒B
            flag = 1;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int i) {
        try {
            lock.lock();
            while (flag != 1) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出,第" + i + "轮开始");
            //打印三次
            for (int j = 0; j < 3; j++) {
                System.out.println("B");
            }
            //唤醒C
            flag = 2;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int i) {
        try {
            lock.lock();
            while (flag != 2) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出,第" + i + "轮开始");
            //打印五次
            for (int j = 0; j < 5; j++) {
                System.out.println("C");
            }
            //唤醒A
            flag = 0;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}