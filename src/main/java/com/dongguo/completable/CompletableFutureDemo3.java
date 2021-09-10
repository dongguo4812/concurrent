package com.dongguo.completable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:03
 * @description:
 */
public class CompletableFutureDemo3 {
    private static Integer num = 10;

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
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程启动 执行加10");
            num += 10;
            return num;
        }).thenApply((i) -> {
            System.out.println("子线程启动 执行平方");
            int num = 10 / 0;
            return i * i;
        }).thenApply((i) -> {
            System.out.println("子线程启动 执行减100");
            return i - 100;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("result:" + v);
            }
        }).exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        System.out.println(future.join());
        System.out.println("主线程结束");
        threadPoolExecutor.shutdown();
    }
}