package com.dongguo.cas;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-17:29
 * @description: 线程解锁 m 之前对变量的写，对于接下来对 m 加锁的其它线程对该变量的读可见
 */
public class HappensBeforeDemo {
    static int x;
    static Object m = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (m) {
                x = 10;
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (m) {
                System.out.println(x);
            }
        }, "t2").start();
    }
}