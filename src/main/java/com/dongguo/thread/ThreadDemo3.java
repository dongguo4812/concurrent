package com.dongguo.thread;

import java.util.concurrent.*;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-21:55
 * @description:
 */
public class ThreadDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建自定义线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            pool.execute(()->{
                System.out.println(Thread.currentThread().getName() + " run");
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}