package com.dongguo.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-16:03
 * @description: CountDownLatch
 */
public class CountDownLatchDemo {

    /**
     * 6个同学陆续离开教室后值班同学才可以关门
     */
    public static void main(String[] args) throws Exception {
        //定义一个数值为6的计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // 创建6个同学
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    if (Thread.currentThread().getName().equals("同学6")) {
                        Thread.sleep(2000);
                    }
                    System.out.println(Thread.currentThread().getName() + "离开了"); //计数器减一,不会阻塞
                    countDownLatch.countDown();
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }, "同学" + i).start();
        }
        //主线程await休息
        System.out.println("主线程睡觉");
        countDownLatch.await();//主线程被唤醒
        //全部离开后自动唤醒主线程
        System.out.println("全部离开了,现在的计数器为" + countDownLatch.getCount());
    }
}
