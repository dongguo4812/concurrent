package com.dongguo.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-13:12
 * @description:
 */
@Slf4j(topic = "d.FixedThreadPoolDemo")
public class FixedThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> f = pool.submit(() -> {
            log.debug("task1");
            int i = 1 / 0;
            return true;
        });
        log.debug("result:{}", f.get());
    }
}