package com.dongguo.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务对象
        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " run");
            return 100;
        });
        // 参数1 是任务对象; 参数2 是线程名字，推荐
        new Thread(task, "t1").start();
        // 主线程阻塞，同步等待 task 执行完毕的结果
        Integer result = task.get();
        System.out.println("结果是:"+ result);
    }
}