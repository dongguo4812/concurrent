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
public class CompletableFutureDemo8 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程启动执行加10");
            num += 10;
            return num;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程启动执行乘10");
            num = num * 10;
            return num;
        });
        //合并两个结果
        CompletableFuture<Object> futureTotal = future.thenCombine(future2, new BiFunction<Integer, Integer, List<Integer>>() {
            @Override
            public List<Integer> apply(Integer a, Integer b) {
                List<Integer> list = new ArrayList<>();
                list.add(a);
                list.add(b);
                return list;
            }
        });
        System.out.println("子线程启动执行加10结果为"+future.get());
        System.out.println("子线程启动执行乘10结果为"+future2.get());
        System.out.println("主线程结束,合并后结果为：" + futureTotal.get());
    }
}