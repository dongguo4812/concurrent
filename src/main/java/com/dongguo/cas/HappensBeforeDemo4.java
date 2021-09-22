package com.dongguo.cas;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-17:35
 * @description: 线程结束前对变量的写，对其它线程得知它结束后的读可见（比如其它线程调用 t1.isAlive() 或 t1.join()等待
 * 它结束）
 */
public class HappensBeforeDemo4 {
    static int x;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            x = 10;
        },"t1");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(x);
    }
}