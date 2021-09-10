package com.dongguo.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/10 0010-15:00
 * @description:
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = ThreadLocal.withInitial(() -> {
        return System.currentTimeMillis();
    });

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) {
        ThreadLocalDemo.begin();//开始时间
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cost: " + ThreadLocalDemo.end() + " mills");
    }
}