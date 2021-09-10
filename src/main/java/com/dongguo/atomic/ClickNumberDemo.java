package com.dongguo.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-16:37
 * @description: 50个线程，每个线程100W次，总点赞数出来
 */
class ClickNumber {
    int number = 0;

    //使用synchronized
    public synchronized void add_Synchronized() {
        number++;
    }
    //atomicInteger
    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void add_atomicInteger() {
        atomicInteger.incrementAndGet();
    }

    //atomicLong
    AtomicLong atomicLong = new AtomicLong(0);

    public void add_AtomicLong() {
        atomicLong.decrementAndGet();
    }

    //longAdder
    LongAdder longAdder = new LongAdder();

    public void add_LongAdder() {
        longAdder.increment();
    }

    //longAccumulator
    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public void add_LongAccumulator() {
        longAccumulator.accumulate(1);
    }
}

public class ClickNumberDemo {
    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();

        long startTime;
        long endTime;
        CountDownLatch countDownLatch = new CountDownLatch(50);
        CountDownLatch countDownLatch2 = new CountDownLatch(50);
        CountDownLatch countDownLatch3 = new CountDownLatch(50);
        CountDownLatch countDownLatch4 = new CountDownLatch(50);
        CountDownLatch countDownLatch5 = new CountDownLatch(50);

        startTime = System.currentTimeMillis();
        for (int i = 1; i <=50; i++) {
            new Thread(() -> {
                try
                {
                    for (int j = 1; j <=100 * 10000; j++) {
                        clickNumber.add_Synchronized();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t add_Synchronized result: "+clickNumber.number);


        startTime = System.currentTimeMillis();
        for (int i = 1; i <=50; i++) {
            new Thread(() -> {
                try
                {
                    for (int j = 1; j <=100 * 10000; j++) {
                        clickNumber.add_AtomicLong();
                    }
                }finally {
                    countDownLatch2.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t add_AtomicLong result: "+clickNumber.number);


        startTime = System.currentTimeMillis();
        for (int i = 1; i <=50; i++) {
            new Thread(() -> {
                try
                {
                    for (int j = 1; j <=100 * 10000; j++) {
                        clickNumber.add_atomicInteger();
                    }
                }finally {
                    countDownLatch5.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch5.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t add_atomicInteger result: "+clickNumber.number);


        startTime = System.currentTimeMillis();
        for (int i = 1; i <=50; i++) {
            new Thread(() -> {
                try
                {
                    for (int j = 1; j <=100 * 10000; j++) {
                        clickNumber.add_LongAdder();
                    }
                }finally {
                    countDownLatch3.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t add_LongAdder result: "+clickNumber.number);

        startTime = System.currentTimeMillis();
        for (int i = 1; i <=50; i++) {
            new Thread(() -> {
                try
                {
                    for (int j = 1; j <=100 * 10000; j++) {
                        clickNumber.add_LongAccumulator();
                    }
                }finally {
                    countDownLatch4.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t add_LongAccumulator result: "+clickNumber.number);

    }
}