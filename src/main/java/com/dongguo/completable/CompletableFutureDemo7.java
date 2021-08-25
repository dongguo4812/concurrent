package com.dongguo.completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:25
 * @description:
 */
public class CompletableFutureDemo7 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程启动");
            num += 10;
            return num;
        });
        ////合并
        CompletableFuture<Integer> future2 = future.thenCompose(i ->
                CompletableFuture.supplyAsync(() -> {
                    return i + 1;
                }));
        //主线程阻塞
        int result = future.get();
        int result2 = future2.get();

        System.out.println("主线程结束,子线程结果为：" + result);
        System.out.println("合并后结果为："+result2);
    }
}