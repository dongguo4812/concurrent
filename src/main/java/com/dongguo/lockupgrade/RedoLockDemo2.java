package com.dongguo.lockupgrade;


import org.openjdk.jol.info.ClassLayout;

import java.util.Vector;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Dongguo
 * @date 2021/9/11 0011-18:06
 * @description: -XX:BiasedLockingStartupDelay=0
 */
class Dog {
}

public class RedoLockDemo2 {
    static Thread t1, t2, t3;

    public static void main(String[] args) throws InterruptedException {
        int loopNumber = 39;
        Vector<Dog> list = new Vector<>();
        t1 = new Thread(() -> {
            for (int i = 0; i < loopNumber; i++) {
                Dog d = new Dog();
                list.add(d);
                synchronized (d) {
                    System.out.println(ClassLayout.parseInstance(d).toPrintable2());
                }
            }

            LockSupport.unpark(t2);
        }, "t1");
        t1.start();
        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("-----------");
            for (int i = 0; i < loopNumber; i++) {
                Dog d = list.get(i);
                System.out.println(i + "加锁前  " + ClassLayout.parseInstance(d).toPrintable2());
                synchronized (d) {
                    System.out.println(i + "加锁中  " + ClassLayout.parseInstance(d).toPrintable2());
                }
                System.out.println(i + " 加锁后 " + ClassLayout.parseInstance(d).toPrintable2());
            }

            LockSupport.unpark(t3);
        }, "t2");
        t2.start();
        t3 = new Thread(() -> {
            LockSupport.park();
            System.out.println("-----------");
            for (int i = 0; i < loopNumber; i++) {
                Dog d = list.get(i);
                System.out.println(i + "加锁前  " + ClassLayout.parseInstance(d).toPrintable2());
                synchronized (d) {
                    System.out.println(i + "加锁中  " + ClassLayout.parseInstance(d).toPrintable2());
                }
                System.out.println(i + " 加锁后 " + ClassLayout.parseInstance(d).toPrintable2());
            }
        }, "t3");
        t3.start();
        t3.join();

        System.out.println(ClassLayout.parseInstance(new Dog()).toPrintable2());
    }
}