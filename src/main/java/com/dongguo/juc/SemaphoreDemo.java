package com.dongguo.juc;

import java.util.concurrent.Semaphore;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-16:14
 * @description: Semaphore
 */
public class SemaphoreDemo {
    /**
     * 抢车位, 10部汽车3个停车位
     */
    public static void main(String[] args) throws Exception {
        //定义3个停车位
        Semaphore semaphore = new Semaphore(3);
        //模拟6辆汽车停车
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(100);
            //停车
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "找车位ing");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "汽车停车成功!");
                    Thread.sleep(3000);//停车时间
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "溜了溜了");
                    semaphore.release();
                }
            }, "汽车" + i).start();
        }
    }
}