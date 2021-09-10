package com.dongguo.completable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:30
 * @description:
 */
public class CompletableFutureAPIDemo1 {

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return 20;
        }), (r1, r2) -> {
            return r1 + r2;
        });
        System.out.println("合并结果为" + future.join());
    }
}