package com.dongguo.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-10:36
 * 题目：实现一个自旋锁
 * 自旋锁好处：循环比较获取没有类似wait的阻塞。
 * <p>
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进来后发现
 * 当前有线程持有锁，不是null，所以只能通过自旋等待，直到A释放锁后B随后抢到。
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void MyLock() {
        System.out.println(Thread.currentThread().getName() + " come in  ");
        //修改不成功
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
            //循环等待（自旋）
        }
        //修改成功
        System.out.println(Thread.currentThread().getName() + "加锁成功");
    }

    public void MyUnLock() {
        atomicReference.compareAndSet(Thread.currentThread(), null);
        System.out.println(Thread.currentThread().getName() + "释放锁成功");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.MyLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.MyUnLock();
        }, "t1").start();

        //暂停一会儿线程，保证A线程先于B线程启动并完成
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.MyLock();
            spinLockDemo.MyUnLock();
        }, "t2").start();
    }
}