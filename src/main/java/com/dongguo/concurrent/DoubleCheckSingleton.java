package com.dongguo.concurrent;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * @author Dongguo
 * @date 2021/9/7 0007-7:38
 * @description:
 */
public class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton singleton;
    //私有化构造方法
    private DoubleCheckSingleton() {
    }
    //双重锁设计
    public static DoubleCheckSingleton getInstance() {
        if (singleton == null) {
            //1.多线程并发创建对象时，会通过加锁保证只有一个线程能创建对象
            synchronized (DoubleCheckSingleton.class) {
                if (singleton == null) {
                    //隐患：多线程环境下，由于重排序，该对象可能还未完成初始化就被其他线程读取
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        //2.对象创建完毕，执行getInstance()将不需要获取锁，直接返回创建对象
        return singleton;
    }
}