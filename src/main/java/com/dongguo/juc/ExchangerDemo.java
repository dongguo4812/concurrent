package com.dongguo.juc;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dongguo
 * @date 2021/9/19 0019-11:34
 * @description:
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        //模拟两个线程组
        for (int i = 1; i <= 2; i++) {
            executor.execute(() -> {
                try {
                    int num = new Random().nextInt(10);
                    System.out.println(Thread.currentThread().getName() + "开始交换数据:" + num);
                    num = exchanger.exchange(num);//交换数据并得到交换后的数据
                    System.out.println(Thread.currentThread().getName() + "交换数据结束后的数据：" + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        executor.shutdown();
    }
}