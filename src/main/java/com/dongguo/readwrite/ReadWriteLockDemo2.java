package com.dongguo.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-19:44
 * @description: ReadWriteLock
 */
public class ReadWriteLockDemo2 {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //创建线程放数据
        for (int i = 0; i < 10; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, "Thread写"+i).start();
        }
        //创建线程取数据
        for (int i = 0; i < 10; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, "Thread读"+(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "1", num + "1");
            }, "Thread读后写"+(i)).start();
        }
    }

    //资源类
    static class MyCache {
        //创建map集合
        private volatile Map<String, Object> map = new HashMap<>();
        //创建锁对象
        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


        //放数据
        public void put(String key, Object value) {
            //添加锁
            readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "写入：" + key);
                //暂停一会
                TimeUnit.MILLISECONDS.sleep(500);
                //放数据
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "写完" + key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁
                readWriteLock.writeLock().unlock();
            }
        }

        //取数据
        public Object get(String key) {
            //添加锁
            readWriteLock.readLock().lock();
            Object result = null;
            try {
                System.out.println(Thread.currentThread().getName() + "取出：" + key);
                result = map.get(key);
                //暂停一会
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(Thread.currentThread().getName() + "取完" + key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放锁
                readWriteLock.readLock().unlock();
            }
            return result;
        }
    }
}