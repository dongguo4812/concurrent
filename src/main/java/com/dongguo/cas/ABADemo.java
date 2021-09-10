package com.dongguo.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-12:48
 * @description:
 */
public class ABADemo {
    public static void main(String[] args) {
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(10, 1);
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();//首次版本号
            System.out.println(Thread.currentThread().getName() + "   1首次版本号" + stamp + "值为" + atomicStampedReference.getReference());
            //保证t1、t2得到相同版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(10, 100, stamp, stamp + 1);
            stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "   2次版本号" + stamp + "值为" + atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(100, 10, stamp, stamp + 1);
            stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "   3次版本号" + stamp + "值为" + atomicStampedReference.getReference());
        }, "t1").start();
        //保证t1发生在t2之前
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();//首次版本号
            System.out.println(Thread.currentThread().getName() + "   1首次版本号" + stamp + "值为" + atomicStampedReference.getReference());
            //保证t1、t2得到相同版本号  并且t1发生ABA问题
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(10, 123, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "修改结果为" + result + " 值为" + atomicStampedReference.getReference());
        }, "t2").start();
    }
}