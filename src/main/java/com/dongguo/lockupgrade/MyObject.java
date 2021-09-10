package com.dongguo.lockupgrade;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Dongguo
 * @date 2021/9/8 0008-21:46
 * @description:
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }
        }, "t1").start();
    }
}