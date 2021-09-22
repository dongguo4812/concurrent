package com.dongguo.unsafe;



import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;



/**
 * @author Dongguo
 * @date 2021/9/7 0007-14:41
 * 以一种线程安全的方式操作非线程安全对象的某些字段。
 * 需求：
 * 1000个人同时向一个账号转账一元钱，那么累计应该增加1000元，
 * 除了synchronized和CAS,还可以使用AtomicIntegerFieldUpdater来实现。
 */
class BankAccount {
    private String bankName = "ACBC";
    public volatile int money;
    static final Unsafe unsafe;
    static final long DATA_OFFSET;

    static {
        unsafe = UnsafeAccessor.getUnsafe();
        try {
            // money 属性在 BankAccount 对象中的偏移量，用于 Unsafe 直接访问该属性
            DATA_OFFSET = unsafe.objectFieldOffset(BankAccount.class.getDeclaredField("money"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    public BankAccount(int money) {
        this.money = money;
    }

    public void transferMoney(int amount) {
        int oldValue;
        while (true) {
            // 获取共享变量旧值，可以在这一行加入断点，修改 data 调试来加深理解
            oldValue = money;
            // cas 尝试修改 data 为 旧值 + amount，如果期间旧值被别的线程改了，返回 false
            if (unsafe.compareAndSwapInt(this, DATA_OFFSET, oldValue, oldValue + amount)) {
                return;
            }
        }
    }
}
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(0);

        for (int i = 1; i <= 1000; i++) {
            new Thread(() -> {
                bankAccount.transferMoney(1);
            }, String.valueOf(i)).start();
        }
        //暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(bankAccount.money);
    }

}