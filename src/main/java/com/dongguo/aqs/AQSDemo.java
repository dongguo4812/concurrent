package com.dongguo.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/9/9 0009-12:34
 * @description:
 */
public class AQSDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
//A、B、C三个顾客去银行办理业务，A先到，此时窗口无人，优先获得办理业务的机会
        //A长期占用窗口
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("A come in");
                TimeUnit.MINUTES.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();
//B发现业务窗口被A占用，去候客区等待，进入AQS队列，等待A办理完成，再尝试抢占受理窗口
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("B come in");
            } finally {
                lock.unlock();
            }
        }, "B").start();
        //C同B
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("C come in");
            } finally {
                lock.unlock();
            }
        }, "C").start();


    }
}