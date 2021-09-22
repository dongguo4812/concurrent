package com.dongguo.sync;

import java.util.ArrayList;

/**
 * @author Dongguo
 * @date 2021/9/11 0011-14:45
 * @description:
 */
public class UnSafeDemo {
    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
        ThreadUnsafe test = new ThreadUnsafe();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "t" + i).start();
        }
    }
}

class ThreadUnsafe {
    public void method1(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            // { 临界区, 会产生竞态条件
            method2(list);
            method3(list);
            // } 临界区
        }
    }

    private void method2(ArrayList<String> list) {
        list.add("1");
    }

    private void method3(ArrayList<String> list) {
        list.remove(0);
    }
}