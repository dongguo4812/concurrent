package com.dongguo.synclock;

import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-12:46
 * @description:
 */
public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"ThreadA").start();
        Thread.sleep(100);
        new Thread(()->{
            try {

                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"ThreadB").start();
    }
}

class Phone {
    public static  synchronized void sendSMS() throws Exception {
        //停留四秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }

    public  synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}