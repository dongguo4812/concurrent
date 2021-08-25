package com.dongguo.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/8/23 0023-22:31
 * @description: Lock实现线程通信
 */
public class ThreadDemo3 {
    static boolean flag = true;

    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
        new Thread(() -> {
            demoClass.increment();
        }, "ThreadA").start();

        new Thread(() -> {
            demoClass.decrement();
        }, "ThreadB").start();
    }

    static class DemoClass {
        //加减对象
        private int number = 0;
        // 声明锁
        private Lock lock = new ReentrantLock();
        // 声明钥匙
        private Condition condition = lock.newCondition();

        //  加1
        public void increment() {
            try {
                lock.lock();
                while (true) {
                    while (number != 0) {
                        condition.await();
                    }
                    if (flag) {
                        number++;
                        System.out.println(Thread.currentThread().getName() + "+1值为：" + number);
                        flag = false;
                    }
                    condition.signalAll();
                }
            } catch (
                    Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void decrement() {
            lock.lock();
            try {
                while (true) {
                    while (number == 0) {
                        condition.await();
                    }
                    if (!flag) {
                        number--;
                        System.out.println(Thread.currentThread().getName() + "-1值为：" + number);
                        flag = true;
                    }
                    condition.signalAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}