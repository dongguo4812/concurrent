package com.dongguo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:12
 * @description:
 */
public class CompletableFutureDemo {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {

            System.out.println("子线程启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num += 10;
            return num;
        }).thenApply((f) -> {
            return f + 1;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("result= " + v);
            }
        });
        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:暂停3秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}