package com.dongguo.threadlocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/9/8 0008-11:54
 * @description:
 */
class MyObject {
    //一般开发中不用调用这个方法
    @Override
    protected void finalize() throws Throwable {
        System.out.println(Thread.currentThread().getName() + "\t" + "---finalize method invoked....");
    }
}

public class ReferenceDemo {
    public static void main(String[] args) {
        //当我们内存不够用的时候，soft会被回收的情况，设置我们的内存大小：-Xms10m -Xmx10m
        ReferenceQueue referenceQueue =new ReferenceQueue();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(new MyObject(),referenceQueue);

        //PhantomReference的get方法总是返回null，因此无法访问对应的引用对象。
        System.out.println("-----gc before内存够用:: " + phantomReference.get());

        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true)
            {
                //每过500毫秒创建一个1M的byte数组
                list.add(new byte[1 * 1024 * 1024]);
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(phantomReference.get());
            }
        },"t1").start();

        new Thread(() -> {
            while (true)
            {
                //通过referenceQueue查看虚对象被回收的情况
                Reference<? extends MyObject> reference = referenceQueue.poll();
                //当内存不足前触发GC，phantomReference进入referenceQueue ，referenceQueue中有虚引用对象
                if (reference != null) {
                    System.out.println("***********有虚对象加入队列了");
                }
            }
        },"t2").start();
    }
}
