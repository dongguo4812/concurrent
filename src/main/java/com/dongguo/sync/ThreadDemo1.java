package com.dongguo.sync;

/**
 * @author Dongguo
 * @date 2021/8/23 0023-21:31
 * @description: volatile实现线程通信
 */
public class ThreadDemo1 {
    private static volatile boolean flag = true;

    private static int number = 0;

    public static void main(String[] args) {
        //ThreadA+1
        new Thread(() -> {
            while (true) {
                if (flag) {
                    number++;
                    System.out.println(Thread.currentThread().getName()+"+1值为：" + number);
                    flag = false;
                }
            }
        }, "ThreadA").start();
        //ThreadB-1
        new Thread(() -> {
            while (true) {
                if (!flag) {
                    number--;
                    System.out.println(Thread.currentThread().getName()+"-1值为：" + number);
                    flag = true;
                }
            }
        }, "ThreadB").start();
    }
}