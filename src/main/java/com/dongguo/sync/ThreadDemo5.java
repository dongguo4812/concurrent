package com.dongguo.sync;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-17:15
 * @description: LockSupport示例
 */
public class ThreadDemo5 {
    static boolean flag = true;
    static Thread threadA;
    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
         threadA = new Thread(() -> {
            demoClass.increment();
        }, "ThreadA");
        threadA.start();
        new Thread(() -> {
            demoClass.decrement();
        }, "ThreadB").start();
    }

    static class DemoClass {
        private int number = 0;

        //加一
        public  void increment() {
            try {
                while (true) {
                    while (number != 0) {
                        LockSupport.park();
                    }
                    if (flag) {
                        number++;
                        System.out.println(Thread.currentThread().getName() + "+1值为：" + number);
                        flag = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //减一
        public  void decrement() {
            try {
                while (true) {


                    if (!flag) {
                        number--;
                        System.out.println(Thread.currentThread().getName() + "-1值为：" + number);
                        flag = true;
                    }
                    LockSupport.unpark(threadA);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}