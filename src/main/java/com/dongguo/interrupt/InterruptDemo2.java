package com.dongguo.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/6 0006-13:37
 * @description:
 */
public class InterruptDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            System.out.println("isInterrupted is false 程序正常运行");


        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}