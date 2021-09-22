package com.dongguo.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-16:03
 * @description: CountDownLatch
 */
public class CountDownLatchDemo2 {

    /**
     * 6个同学陆续离开教室后值班同学才可以关门
     */
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(4);
        //定义一个数值为6的计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // 创建6个同学
        for (int i = 1; i <= 6; i++) {
            try {
                int count =i;
                service.submit(() -> {
                    if (Thread.currentThread().getName().equals("同学6")) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() +"同学"+count+ "离开了"); //计数器减一,不会阻塞
                    countDownLatch.countDown();
                });
            } catch (
                    Exception e) {
                e.printStackTrace();
            }
        }
        //主线程await休息
        System.out.println("主线程睡觉");
        countDownLatch.await();//主线程被唤醒
        //全部离开后自动唤醒主线程
        System.out.println("全部离开了,现在的计数器为" + countDownLatch.getCount());
        service.shutdown();
    }
}
