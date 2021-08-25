package com.dongguo.completable;

import java.util.concurrent.CompletableFuture;


/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:46
 * @description:
 */
public class CompletableFutureDemo10 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        CompletableFuture<Integer>[] futures = new CompletableFuture[4];

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("子线程启动执行加10");
                num += 10;
                return num;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        });
        futures[0] = future;
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("子线程启动执行乘10");
                num = num * 10;
                return num;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        });
        futures[1] = future2;
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("子线程启动执行减10");
                num -= 10;
                return num;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        });
        futures[2] = future3;
        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
                System.out.println("子线程启动执行除以10");
                num = num / 10;
                return num;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        });
        futures[3] = future4;
        //多任务合并 有一个任务完成就返回
        CompletableFuture<Object> futureOne = CompletableFuture.anyOf(futures);
        System.out.println("主线程结束,合并后结果为：" + futureOne.get());
    }
}