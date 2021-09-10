package com.dongguo.lockupgrade;

/**
 * @author Dongguo
 * @date 2021/9/3 0003-10:14
 * @description: 实现3个售票员卖出100张票的案例
 * 使用同步方法，不存在超卖问题
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            //循环100次保证能够卖光票
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "T1").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "T2").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "T3").start();
    }
}

/**
 * @author Dongguo
 * @description: 资源类
 */
class Ticket {
    private int count = 50;

    public synchronized void saleTicket() {
        if (count > 0) {
            count--;
            System.out.println(Thread.currentThread().getName() + "卖票成功，还剩" + count + "张票！");
        }
    }
}