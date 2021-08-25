package com.dongguo.completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-22:36
 * @description: 没有返回值的异步任务
 */
public class CompletableFutureDemo1 {
    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("子线程启动");
                Thread.sleep(5000);
                System.out.println("子线程完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //主线程阻塞
        future.get();
        System.out.println("主线程结束");
    }
}