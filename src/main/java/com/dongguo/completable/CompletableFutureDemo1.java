package com.dongguo.completable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-22:36
 * @description: 没有返回值的异步任务
 */
public class CompletableFutureDemo1 {
    public static void main(String[] args) {
        //自定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        try {
            System.out.println("主线程开始");
            //运行一个没有返回值的异步任务
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("子线程启动");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程完成");
            }, threadPoolExecutor);
            future.join();
            System.out.println("主线程结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}