package com.dongguo.waitnotify;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-13:36
 * @description:
 */
public class WaitNotifyDemo {
    static Thread t1, t2;

    public static void main(String[] args) {
        Object obj = new Object();
        t1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                    System.out.println("-----------");
                    System.out.println(t1 + "被notifyAll后 main线程释放锁后，状态" + t1.getState());
                    System.out.println(t2 + "被notifyAll后 main线程释放锁后，状态" + t2.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        t2 = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                    System.out.println("-----------");
                    System.out.println(t2 + "被notifyAll后 main线程释放锁后，状态" + t2.getState());
                    System.out.println(t1 + "被notifyAll后 main线程释放锁后，状态" + t1.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1 + "wait后状态" + t1.getState());
        System.out.println(t2 + "wait后状态" + t2.getState());
        synchronized (obj) {
            obj.notifyAll();
            System.out.println("-----------");
            System.out.println(t1 + "被notifyAll后 main线程没有释放锁，状态" + t1.getState());
            System.out.println(t2 + "被notifyAll后 main线程没有释放锁，状态" + t2.getState());
        }
    }
}