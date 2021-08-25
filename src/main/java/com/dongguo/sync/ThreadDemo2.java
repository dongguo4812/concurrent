package com.dongguo.sync;


/**
 * @author Dongguo
 * @date 2021/8/23 0023-21:48
 * @description: synchronized实现线程通信
 */
public class ThreadDemo2 {

    static boolean flag = true;

    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
        new Thread(() -> {
            demoClass.increment();
        }, "ThreadA").start();
        new Thread(() -> {
            demoClass.decrement();
        }, "ThreadB").start();
    }

    static class DemoClass {
        private int number = 0;

        //加一
        public synchronized void increment() {
            try {
                while (true) {
                    while (number != 0) {
                        this.wait();
                    }
                    if (flag) {
                        number++;
                        System.out.println(Thread.currentThread().getName() + "+1值为：" + number);
                        flag = false;
                        notifyAll();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //减一
        public synchronized void decrement() {
            try {
                while (true) {
                    while (number == 0) {
                        this.wait();
                    }
                    if (!flag) {
                        number--;
                        System.out.println(Thread.currentThread().getName() + "-1值为：" + number);
                        flag = true;
                        notifyAll();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}