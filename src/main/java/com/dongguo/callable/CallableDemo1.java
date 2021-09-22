package com.dongguo.callable;

import java.util.concurrent.Callable;

/**
 * @author Dongguo
 * @date 2021/9/18 0018-17:07
 * @description:
 */
public class CallableDemo1 {
}

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable {

    @Override
    public Object call() throws Exception {
        return null;
    }
}