package com.dongguo.completable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:39
 * @description:
 */
public class CompletableFutureDemo9 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        List<CompletableFuture> list = new ArrayList<>();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程启动执行加10");
            num += 10;
            return num;
        });
        list.add(future);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程启动执行乘10");
            num = num * 10;
            return num;
        });
        list.add(future2);
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程启动执行减10");
            num -= 10;
            return num;
        });
        list.add(future3);
        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程启动执行除以10");
            num = num / 10;
            return num;
        });
        list.add(future4);
        //多任务合并
        List<Integer> collect = list.stream().map(CompletableFuture<Integer>::join).collect(Collectors.toList());
        System.out.println("主线程结束,合并后结果为：" + collect);
    }
}