package com.dongguo.completable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-7:56
 * @description: 有返回值的异步任务
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws Exception {
        //自定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("子线程启动");
                Thread.sleep(5000);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "子线程完成";
        },threadPoolExecutor);
        //主线程阻塞
        String result = future.get();
        System.out.println("主线程结束,子线程结果为：" + result);
        threadPoolExecutor.shutdown();
    }
}