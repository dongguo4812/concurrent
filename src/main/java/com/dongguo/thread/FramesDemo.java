package com.dongguo.thread;

/**
 * @author Dongguo
 * @date 2021/9/11 0011-7:51
 * @description:
 */
public class FramesDemo {
    public static void main(String[] args) {
        new Thread(()->{
            m1(2);
        },"t1").start();
        m1(1);
    }

    private static void m1(int x) {
        int y = x + 1;
        Object object = m2();
        System.out.println(object);
    }

    private static Object m2() {
        Object o = new Object();
        return o;
    }
}