package com.dongguo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-18:39
 * @description:
 */
public class AtomicIntegerDemo2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        int result = atomicInteger.updateAndGet(x -> x * 10);
        System.out.println(result);
    }
}