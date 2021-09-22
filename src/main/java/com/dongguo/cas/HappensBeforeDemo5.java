package com.dongguo.cas;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-17:37
 * @description: 线程 t1 打断 t2（interrupt）前对变量的写，对于其他线程得知 t2 被打断后对变量的读可见（通过
 * t2.interrupted 或 t2.isInterrupted）
 */
public class HappensBeforeDemo5 {
    static int x;

    public static void main(String[] args) {
        Thread t2 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(x);
                    break;
                }
            }
        }, "t2");
        t2.start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x = 10;
            t2.interrupt();
        }, "t1").start();
        while (!t2.isInterrupted()) {
            Thread.yield();
        }
        System.out.println(x);
    }
}