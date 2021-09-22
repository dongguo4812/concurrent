package com.dongguo.thread;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-22:58
 * @description: 演示多个线程并发交替执行
 */
public class MultiThreadDemo {
    public static void main(String[] args) {
        new Thread(()->{
           while (true){
               System.out.println(Thread.currentThread().getName() + " run");
           }
        },"t1").start();

        new Thread(()->{
            while (true){
                System.out.println(Thread.currentThread().getName() + " run");
            }
        },"t2").start();
    }
}