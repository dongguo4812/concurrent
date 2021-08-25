package com.dongguo.sync;


/**
 * @author Dongguo
 * @date 2021/8/23 0023-23:01
 * @description: join
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "开始执行");
        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }, "ThreadA");


        Thread threadB = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }, "ThreadB");

        try {
            threadA.start();
            threadA.join();
            threadB.start();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
    }
}
