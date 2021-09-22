package com.dongguo.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-12:43
 * @description:
 */
@Slf4j(topic = "d.ScheduledThreadPoolDemo")
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);//创建两个线程
        Future<?> future = executor.submit(() -> {
            log.debug("task 1");
                int i = 10 / 0;
        });
    }
}