package com.dongguo.juc;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author Dongguo
 * @date 2021/8/24 0024-16:03
 * @description: 模拟10个人匹配LOL进行加载游戏 ，等待10个人全部加载100%后游戏开始
 */
public class CountDownLatchDemo3 {

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Random random = new Random();
        String[] all = new String[10];
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int j = 0; j < 10; j++) {//10人
            int k = j;
            service.submit(()->{
                for (int i = 0; i <=100 ; i++) {
                    try {
                        //随机睡眠100毫秒以内
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    all[k] = i+"%";
                    System.out.print("\r"+ Arrays.toString(all));
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("\n游戏开始");
        service.shutdown();
    }
}
