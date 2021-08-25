package com.dongguo.completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:12
 * @description:
 */
public class CompletableFutureDemo5 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {

            System.out.println("子线程启动");
            int i = 1 / 0;
            num += 10;
            return num;
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return -1;
        });
        //主线程阻塞
        int result = future.get();
        System.out.println("主线程结束,子线程结果为：" + result);
    }
}