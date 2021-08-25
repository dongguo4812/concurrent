package com.dongguo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-22:14
 * @description:
 */

class MyTask extends RecursiveTask<Integer> {

    private static final Integer VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分合并
    @Override
    protected Integer compute() {
        if ((end - begin) <= VALUE) {
            //不用拆分 相加操作
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            //进行拆分
            int middle = (begin + end) / 2;
            //拆分左边
            MyTask task1 = new MyTask(begin, middle);
            //拆分右边
            MyTask task2 = new MyTask(middle + 1, end);
            task1.fork();
            task2.fork();
            //合并
            result = task1.join() + task2.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(0,100);
        //创建分之合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        Integer result = forkJoinTask.get();
        System.out.println(result);
        //关闭池对象
        forkJoinPool.shutdown();


    }
}