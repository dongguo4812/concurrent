package com.dongguo.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-15:17
 * @description:
 */
public class LongAdderAPIDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        System.out.println(longAdder.longValue());
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.longValue());
    }
}