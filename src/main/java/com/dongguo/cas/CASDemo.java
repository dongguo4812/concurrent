package com.dongguo.cas;


import org.openjdk.jol.info.ClassLayout;

/**
 * @author Dongguo
 * @date 2021/9/4 0004-16:24
 * @description:
 */
public class CASDemo {
    public static void main(String[] args) {
        Object object = new Object();
        //使用ClassLayout解析object并转成可以打印的格式
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        //给对象上锁
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}