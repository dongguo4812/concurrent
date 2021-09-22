package com.dongguo.cas;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-17:32
 * @description: 线程对 volatile 变量的写，对接下来其它线程对该变量的读可见
 */
public class HappensBeforeDemo2 {
    volatile static int x;
    public static void main(String[] args) {

        new Thread(()->{
            x = 10;
        },"t1").start();
        new Thread(()->{
            System.out.println(x);
        },"t2").start();
    }
}