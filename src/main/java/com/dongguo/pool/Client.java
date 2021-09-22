package com.dongguo.pool;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-9:11
 * @description:
 */
public class Client {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1,
                1000, TimeUnit.MILLISECONDS, 1, (queue, task) -> {
            // 1. 死等
//             queue.put(task);
            // 2) 带超时等待
//             queue.offer(task, 1500, TimeUnit.MILLISECONDS);
            // 3) 让调用者放弃任务执行
//            System.out.println("放弃执行"+task);
            // 4) 让调用者抛出异常
//             throw new RuntimeException("任务执行失败 " + task);
            // 5) 让调用者自己执行任务
            task.run();
        });
        for (int i = 0; i < 3; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(j);
            });
        }
    }
}