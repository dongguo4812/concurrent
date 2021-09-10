package com.dongguo.completable;

import java.util.concurrent.*;

/**
 * @author Dongguo
 * @date 2021/9/5 0005-13:41
 * @description:
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) {
        //自定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        },threadPoolExecutor).applyToEither(CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        }),(r)->{
            return r;
        });

        try {
            System.out.println(future.join());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}