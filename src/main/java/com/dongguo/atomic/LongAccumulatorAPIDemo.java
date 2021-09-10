package com.dongguo.atomic;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-15:27
 * @description:
 */
public class LongAccumulatorAPIDemo {
    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator((x, y) -> {
            return x - y;
        }, 100);
        accumulator.accumulate(1);
        accumulator.accumulate(2);
        accumulator.accumulate(3);

        System.out.println(accumulator.longValue());
    }
}