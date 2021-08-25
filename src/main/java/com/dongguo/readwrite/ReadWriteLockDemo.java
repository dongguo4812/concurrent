package com.dongguo.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-19:44
 * @description: ReadWriteLock
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //创建线程放数据
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, "Thread"+i).start();
        }
        //创建线程取数据
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, "Thread"+(i + 5)).start();
        }
    }

    //资源类
    static class MyCache {
        //创建map集合
        private volatile Map<String, Object> map = new HashMap<>();
        //创建读写锁对象
        private ReadWriteLock rwLock = new ReentrantReadWriteLock();

        //放数据
        public void put(String key, Object value) {
            //添加写锁
            rwLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "写入：" + key);
                //暂停一会
                TimeUnit.MICROSECONDS.sleep(300);
                //放数据
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "写完" + key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放写锁
                rwLock.writeLock().unlock();
            }
        }

        //取数据
        public Object get(String key) {
            //添加读锁
            rwLock.readLock().lock();
            Object result = null;
            try {
                System.out.println(Thread.currentThread().getName() + "取出：" + key);
                //暂停一会
                TimeUnit.MICROSECONDS.sleep(300);
                result = map.get(key);
                System.out.println(Thread.currentThread().getName() + "取完" + key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放读锁
                rwLock.readLock().unlock();
            }
            return result;
        }
    }
}