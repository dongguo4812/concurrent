package com.dongguo.lock;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-9:06
 * @description: Lock实现线程间定制化通信
 */
public class ThreadDemo {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.printA(i);
            }
        }, "A线程").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.printB(i);
            }
        }, "B线程").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.printC(i);
            }
        }, "C线程").start();
    }
}

//创建资源类
class  ShareResource {
    //定义flag 0--打印A 1---打印B 2----打印C
    private int flag = 0;
    // 声明锁
    private Lock lock = new ReentrantLock();
    // 声明钥匙A
    private Condition conditionA = lock.newCondition();
    // 声明钥匙B
    private Condition conditionB = lock.newCondition();
    // 声明钥匙C
    private Condition conditionC = lock.newCondition();

    public void printA(int j) {
        try {
            lock.lock();
            while (flag != 0) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出A,第" + j + "轮开始");
            //输出2次A
            for (int i = 0; i < 2; i++) {
                System.out.println("A");
            }
            // 开始打印B
            flag = 1;
            // 唤醒B
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int j) {
        try {
            lock.lock();
            while (flag != 1) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出B,第" + j + "轮开始");
            //输出3次B
            for (int i = 0; i < 3; i++) {
                System.out.println("B");
            }
            // 开始打印C
            flag = 2;
            // 唤醒C
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int j) {
        try {
            lock.lock();
            while (flag != 2) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出C,第" + j + "轮开始");
            //输出5次C
            for (int i = 0; i < 5; i++) {
                System.out.println("C");
            }
            // 开始打印A
            flag = 0;
            // 唤醒C
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}