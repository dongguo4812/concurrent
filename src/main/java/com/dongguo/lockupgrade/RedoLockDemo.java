package com.dongguo.lockupgrade;



import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/11 0011-18:06
 * @description:  -XX:BiasedLockingStartupDelay=0
 */
public class RedoLockDemo {
    public static void main(String[] args) {
        Object object = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
                    object.wait();
                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }, "t1");
        t1.start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object) {
                object.notifyAll();
                System.out.println("notifyAll");
            }
        }, "t2").start();
    }
}