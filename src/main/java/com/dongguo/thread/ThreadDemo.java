package com.dongguo.thread;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo {
    public static void main(String[] args) {
        // 构造方法的参数是给线程指定名字，推荐
        Thread t1 = new Thread("t1") {
            @Override
            // run 方法内实现了要执行的任务
            public void run() {
                System.out.println(Thread.currentThread().getName()+" run");
            }
        };
        t1.start();
    }
}