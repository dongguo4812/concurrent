package com.dongguo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-14:44
 * @description:
 */
@Slf4j(topic = "d.Client")
public class Client {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            log.debug("locking...");
            lock.lock();
            log.debug("locking...");
            try {
                try {
                    //t1睡眠1s
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                log.debug("unlocking...");
                lock.unlock();
                log.debug("unlocking...");
                lock.unlock();
            }
        }, "t1").start();
    }
}