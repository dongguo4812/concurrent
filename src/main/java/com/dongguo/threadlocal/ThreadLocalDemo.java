package com.dongguo.threadlocal;

/**
 * @author Dongguo
 * @date 2021/9/8 0008-7:08
 * @description:
 */
class House {

    //不建议使用
//    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return 10;
//        }
//    };
    //1.8新增创建threadLocal方法
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public ThreadLocal<Integer> getThreadLocal() {
        return threadLocal;
    }

    public void saleHouse() {
        Integer value = threadLocal.get();
        value++;
        threadLocal.set(value);
    }
}

public class ThreadLocalDemo {
    public static void main(String[] args) {
        House house = new House();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName() + "卖出" + house.getThreadLocal().get());
            } finally {
                house.getThreadLocal().remove();//如果不清理自定义的 ThreadLocal 变量，可能会影响后续业务逻辑和造成内存泄露等问题
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName() + "卖出" + house.getThreadLocal().get());
            } finally {
                house.getThreadLocal().remove();
            }
        }, "t2").start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 15; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName() + "卖出" + house.getThreadLocal().get());
            } finally {
                house.getThreadLocal().remove();
            }
        }, "t3").start();
    }
}