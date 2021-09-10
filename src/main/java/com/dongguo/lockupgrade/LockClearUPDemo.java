package com.dongguo.lockupgrade;

/**
 * @author Dongguo
 * @date 2021/9/9 0009-10:29
 * @description: 锁消除
 * 从JIT角度看相当于无视它，synchronized (o)不存在了,这个锁对象并没有被共用扩散到其它线程使用，
 * 极端的说就是根本没有加这个锁对象的底层机器码，消除了锁的使用
 */

public class LockClearUPDemo {
    static Object objectLock = new Object();//正常的

    public void m1() {
        //锁消除,JIT会无视它，synchronized(对象锁)不存在了。不正常的
        Object o = new Object();

        synchronized (o) {
            System.out.println("-----hello LockClearUPDemo" + "\t" + o.hashCode() + "\t" + objectLock.hashCode());
        }
    }

    public static void main(String[] args) {
        LockClearUPDemo demo = new LockClearUPDemo();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                demo.m1();
            }, String.valueOf(i)).start();
        }
    }
}

