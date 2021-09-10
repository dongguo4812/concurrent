package com.dongguo.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-14:14
 * @description:
 */
public class AtomicMarkableReferenceDemo {
    public static void main(String[] args) {
        AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(1,false);

        new Thread(()->{
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"修改标志为"+marked);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicMarkableReference.compareAndSet(1,100,marked,!marked);
            System.out.println("值为"+atomicMarkableReference.getReference());
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"修改标志为"+marked);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicMarkableReference.compareAndSet(1, 123, marked, !marked);
            System.out.println("修改结果"+result);
            System.out.println(Thread.currentThread().getName()+"修改标志为"+atomicMarkableReference.isMarked());
            System.out.println("值为"+atomicMarkableReference.getReference());
        },"t2").start();
    }
}