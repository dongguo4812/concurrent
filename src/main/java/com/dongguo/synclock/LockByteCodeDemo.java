package com.dongguo.synclock;

/**
 * @author Dongguo
 * @date 2021/9/5 0005-21:23
 * @description: 字节码角度分析synchronized
 */
public class LockByteCodeDemo {

    Object object = new Object();

    //同步代码块
//    public void m1() {
//        synchronized (object) {
//            System.out.println("同步代码块");
//            //抛出异常
//            throw new RuntimeException("出现异常");
//        }
//    }
    //普通同步方法
//    public synchronized void m2() {
//        System.out.println("普通同步方法");
//    }
    //静态同步方法
    public static synchronized void m3() {
        System.out.println("静态同步方法");
    }

    public static void main(String[] args) {

    }
}