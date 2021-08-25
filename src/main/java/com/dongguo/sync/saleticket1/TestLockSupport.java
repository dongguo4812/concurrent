package com.dongguo.sync.saleticket1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
/**
 * @author Dongguo
 * @date 2021/8/24 0024 17:17
 * @description:
 */
public class TestLockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i==5)
                    LockSupport.park();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t.start();
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("8s sleep done");
        LockSupport.unpark(t);

    }
}