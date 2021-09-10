package com.dongguo.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/6 0006-13:32
 * @description:
 */
public class InterruptDemo1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    // isInterrupted = true  执行线程中断相关逻辑
                    System.out.println("isInterrupted is true 程序结束");
                    break;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println("isInterrupted is false 程序正常运行");
                } catch (InterruptedException e) {
                    //在捕获异常时再次调用interrupt方法才能立即中断,否则程序不会停止，也就是中断不会被打断
                    System.out.println("再次调用interrupt方法打断");
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }

            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}