package com.dongguo.threadlocal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-15:24
 * @description:
 */
public class threadDemo1 {
    public static void main(String[] args) {
        //Runnable接口创建线程
        new Thread(new MyThread(), "ThreadA").start();
        //Callable接口创建线程
        FutureTask<Integer> task1 = new FutureTask<>(new MyThread2());
        //lambda表达式实现
        FutureTask<Integer> task2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "使用callable");
            return 20;
        });
        //创建一个线程

        new Thread(task2, "ThreadC").start();
        new Thread(task1, "ThreadB").start();
        try {
            //可以多次get获得返回值
            System.out.println(task1.get());
            System.out.println(task2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "完成");
    }
}

//创建新类MyThread实现runnable接口
class MyThread implements Runnable {
    @Override
    public void run() {
    }
}

//新类MyThread2实现callable接口
class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "使用callable");
        return 200;
    }
}