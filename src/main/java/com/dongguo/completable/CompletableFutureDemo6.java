package com.dongguo.completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author Dongguo
 * @date 2021/8/25 0025-8:16
 * @description:
 */
public class CompletableFutureDemo6 {
    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");
        //运行一个没有返回值的异步任务
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {

            System.out.println("子线程启动");
            int i = 1 / 0;
            num += 10;
            return num;
        }).handle((i, e) -> {
            if (e != null) {
                System.out.println("发生了异常,内容为:" + e.getMessage());
                return -1;
            } else {
                System.out.println("正常完成,内容为: " + i);
                return i;
            }
        });
        //主线程阻塞
        int result = future.get();
        System.out.println("主线程结束,子线程结果为：" + result);
    }
}