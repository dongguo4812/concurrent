package com.dongguo.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-9:05
 * @description:
 */
public class CASDemo2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.compareAndSet(0,10);

    }
}