package com.dongguo.interrupt;

/**
 * @author Dongguo
 * @date 2021/9/6 0006-14:27
 * @description:
 */
public class InterruptDemo3 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"---"+Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"---"+Thread.interrupted());
        System.out.println("111111");
        Thread.currentThread().interrupt();
        System.out.println("222222");
        System.out.println(Thread.currentThread().getName()+"---"+Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"---"+Thread.interrupted());
    }
}