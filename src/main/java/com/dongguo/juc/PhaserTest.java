package com.dongguo.juc;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/19 0019-11:40
 * @description:
 */
public class PhaserTest {

    private static Phaser phaser = new Phaser();

    public static void main(String args[]) {
        for (int i = 1; i <= 5; i++) {
          new Thread(()->{
              phaser.register();

              System.out.println(Thread.currentThread().getName() +" is working");
              try {
                  TimeUnit.SECONDS.sleep(2);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName() +" work finished "+System.currentTimeMillis());
              phaser.arriveAndAwaitAdvance();//等待
          },"t"+i).start();
        }
    }
}