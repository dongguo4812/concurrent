package com.dongguo.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/4 0004-7:10
 * @description: Callable创建线程
 */
public class CallableDemo {
    public static void main(String[] args) {
        try {
            //Lambda表达式
            FutureTask task1 = new FutureTask(() -> {
                System.out.println("Lambda表达式 Callable创建线程1");
                return 10;
            });
            new Thread(task1, "t1").start();

            FutureTask task2 = new FutureTask(() -> {
                System.out.println("Lambda表达式 Callable创建线程2");
                return 20;
            });
            new Thread(task2, "t2").start();

            System.out.println(Thread.currentThread().getName() + "计算完成");
            System.out.println(task1.get());
            System.out.println(task2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
