package com.dongguo.juc;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/19 0019-12:42
 * @description:
 */
public class PhaserTest1 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);

        for (int i = 1; i <= 3; i++) {
            int no =i;
            new Thread(() -> {
                try {
                    System.out.println(no+": 当前处于第："+phaser.getPhase()+"阶段");
                    System.out.println(no+": start running");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(no+": end running");
                    //等待其他运动员完成跑步
                    phaser.arriveAndAwaitAdvance();

                    System.out.println(no+": 当前处于第："+phaser.getPhase()+"阶段");
                    System.out.println(no+": start bicycle");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(no+": end bicycle");
                    //等待其他运动员完成骑行
                    phaser.arriveAndAwaitAdvance();

                    System.out.println(no+": 当前处于第："+phaser.getPhase()+"阶段");
                    System.out.println(no+": start long jump");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(no+": end long jump");
                    //等待其他运动员完成跳远
                    phaser.arriveAndAwaitAdvance();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "t"+i).start();
        }
    }
}