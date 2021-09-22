package com.dongguo.lockupgrade;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-9:01
 * @description:
 */
public class demo {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable2());
//        new Thread(()->{
//            synchronized (o) {
//                System.out.println(ClassLayout.parseInstance(o).toPrintable2());
//            }
//        },"t1").start();
//
//
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(()->{
//            synchronized (o) {
//                System.out.println(ClassLayout.parseInstance(o).toPrintable2());
//            }
//        },"t2").start();
    }
}