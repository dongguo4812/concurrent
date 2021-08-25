package com.dongguo.completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:03
 * @description: 先对一个数加10, 然后取平方
 */
public class CompletableFutureDemo3 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("子线程启动 执行加10");
                num += 10;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return num;
        }).thenApply((i) -> {
            return i * i;
        });
        //主线程阻塞
        Integer result = future.get();
        System.out.println("主线程结束,子线程结果为：" + result);
    }
}