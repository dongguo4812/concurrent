package com.dongguo.cas;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-17:33
 * @description: 线程 start 前对变量的写，对该线程开始后对该变量的读可见
 */
public class HappensBeforeDemo3 {
    static int x;

    public static void main(String[] args) {

        x = 10;
        new Thread(() -> {
            System.out.println(x);
        }, "t2").start();
    }
}