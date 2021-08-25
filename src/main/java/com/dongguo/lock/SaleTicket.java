package com.dongguo.lock;

/**
 * @author Dongguo
 * @date 2021/8/23 0023-19:03
 * @description: 售票
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //使用lambda表达式
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "ThreadA").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "ThreadB").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                ticket.saleTicket();
            }
        }, "ThreadC").start();
    }
}