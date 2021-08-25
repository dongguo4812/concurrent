package com.dongguo.completable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:08
 * @description: 消费处理结果thenAccept
 */
public class CompletableFutureDemo4 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("子线程启动 执行加10");
                num += 10;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return num;
        }).thenApply((i) -> {
            return i * i;
        }).thenAccept(new Consumer<Integer>(){

            @Override
            public void accept(Integer integer) {
                System.out.println("子线程全部处理完成,最后调用了accept,结果为:" + integer);
            }
        });
        //主线程阻塞

        System.out.println("主线程结束");
    }
}