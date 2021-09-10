package com.dongguo.atomic;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-13:41
 * @description:
 */

class MyNumber {
    @Getter
    private AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus() {
        atomicInteger.incrementAndGet();
    }
}

public class AtomicIntegerDemo {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        final Integer THREAD_SIZE = 50;
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_SIZE);
        for (int i = 1; i <= THREAD_SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, "t" + i).start();
        }
        try {
            //保证50个线程执行完再输出结果，不然50个线程还没执行完，主线程就退出了
            countDownLatch.await();
            System.out.println(myNumber.getAtomicInteger().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}