package com.dongguo.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-16:09
 * @description:
 */
public class CyclicBarrierDemo {
    //定义神龙召唤需要的龙珠总数
    private final static int NUMBER = 7;

    //集齐7颗龙珠就可以召唤神龙
    public static void main(String[] args) {
        // 定义循环栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("集齐" + NUMBER + "颗龙珠,现在召唤神龙!!!!!!!!!");
        });
        //定义7个线程分别去收集龙珠
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
//                    if (Thread.currentThread().getName().equals("龙珠3号")) {
//                        System.out.println("龙珠3号抢夺战开始,孙悟空开启超级赛亚人模式!");
                        Thread.sleep(5000);
//                        System.out.println("龙珠3号抢夺战结束,孙悟空打赢了,拿到了龙珠3号!");
//                    } else {
                        System.out.println(Thread.currentThread().getName() + "收集到了!!!!");
//                    }
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "龙珠" + i + "号").start();
        }
    }
}