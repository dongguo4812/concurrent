package com.dongguo.filereader;

/**
 * @author Dongguo
 * @date 2021/8/23 0023-18:34
 * @description: 用户线程和守护线程
 */
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            //isDaemon()判断该线程是否是一个守护线程
            System.out.println(Thread.currentThread().getName() + "---" + Thread.currentThread().isDaemon());
            while (true){
                //死循环
            }
        },"thread1");
        //设置为守护线程
        thread.setDaemon(true);
        thread.start();

        System.out.println(Thread.currentThread().getName()+"执行结束");
    }
}