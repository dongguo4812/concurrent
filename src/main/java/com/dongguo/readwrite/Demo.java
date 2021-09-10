package com.dongguo.readwrite;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dongguo
 * @date 2021/9/4 0004-9:46
 * @description:
 */
public class Demo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //创建线程放数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, "t" + i).start();
        }
        //创建线程取数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, "t" + i).start();
        }
    }

}

class MyCache {
    private volatile HashMap<String, String> hashMap = new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //放数据
    public void put(String k, String v) {
        try {
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "开始写入"+k);
            //暂停一会
            TimeUnit.MICROSECONDS.sleep(300);
            hashMap.put(k, v);
            System.out.println(Thread.currentThread().getName() + "写入完成");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
    //取数据
    public void get(String k) {
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "开始读取");
            String result = hashMap.get(k);
            System.out.println(Thread.currentThread().getName() + "读取完成"+result);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}