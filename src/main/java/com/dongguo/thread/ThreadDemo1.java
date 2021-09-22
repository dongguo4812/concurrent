package com.dongguo.thread;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        // 创建任务对象
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run");
            }
        };
        // 参数1 是任务对象; 参数2 是线程名字，推荐
        Thread t1 = new Thread(task2, "t1");
        t1.start();

        //Lambda表达式
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " run");
        }, "t2");
        t2.start();
    }
}