package com.dongguo.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/6 0006-23:02
 * @description:
 */
public class VolatileSeeDemo {
    //    static boolean flag = true;       //不加volatile，没有可见性
    static volatile boolean flag = true;       //加了volatile，保证可见性

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            while (flag) {
                //死循环
            }
            System.out.println(Thread.currentThread().getName() + "\t flag被修改为false,退出.....");
        }, "t1").start();

        //暂停2秒钟后让main线程修改flag值
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        System.out.println("main线程修改完成");
    }
}