package com.dongguo.cas;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-17:40
 * @description:
 */
public class HappensBeforeDemo6 {
    volatile static int x;
    static int y;
    public static void main(String[] args) {
        new Thread(()->{
            y = 10;
            x = 20;
        },"t1").start();
        new Thread(()->{
        // x=20 对 t2 可见, 同时 y=10 也对 t2 可见
            System.out.println(x);
        },"t2").start();
    }
}