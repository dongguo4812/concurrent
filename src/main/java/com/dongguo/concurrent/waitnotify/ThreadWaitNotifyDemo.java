package com.dongguo.concurrent.waitnotify;

/**
 * @author Dongguo
 * @date 2021/9/3 0003-13:15
 * @description: 生产者消费者
 * 四个线程t1加1，t2减1，t3加1，t4减1
 * 虚假唤醒问题
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        Number number = new Number();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    //为了体现出虚假唤醒问题
                    Thread.sleep(200);
                    number.incry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    number.decry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(400);
                    number.incry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    number.decry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4").start();
    }
}

class Number {
    private int count;

    public synchronized void incry() throws InterruptedException {
        while (count != 0) {
            this.wait();
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "加一成功，count值为：" + count);
        this.notifyAll();
    }

    public synchronized void decry() throws InterruptedException {
        while (count == 0) {
            this.wait();
        }
        count--;
        System.out.println(Thread.currentThread().getName() + "减一成功，count值为：" + count);
        this.notifyAll();
    }
}