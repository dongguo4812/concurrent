package com.dongguo.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-13:56
 * @description:
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3,4,5});
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }

        atomicIntegerArray.getAndSet(0,10);
        System.out.println(atomicIntegerArray.get(0));

        atomicIntegerArray.getAndIncrement(1);
        System.out.println(atomicIntegerArray.get(1));
        int result = atomicIntegerArray.getAndIncrement(1);
        System.out.println(result+"\t"+atomicIntegerArray.get(1));
    }
}