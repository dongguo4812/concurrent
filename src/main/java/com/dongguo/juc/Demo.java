package com.dongguo.juc;


import java.util.Timer;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/4 0004-8:51
 * @description:
 */
public class Demo {

    private final static int NUMBER = 3;
    public static void main(String[] args) {
        //定义3个停车位
        Semaphore semaphore =new Semaphore(NUMBER);

        for (int i = 1; i <=10; i++) {
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(100);//找车位
                    System.out.println(Thread.currentThread().getName() + "找车位ing");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "汽车停车成功!");
                    TimeUnit.SECONDS.sleep(3);//停车时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "离开停车位");
                    semaphore.release();
                }
            },"t"+i).start();
        }
    }
}