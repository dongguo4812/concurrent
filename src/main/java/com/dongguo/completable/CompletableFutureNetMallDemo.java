package com.dongguo.completable;

import lombok.Getter;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Dongguo
 * @date 2021/9/5 0005-11:16
 * @description: 案例 电商比价需求
 * 功能到性能
 */
public class CompletableFutureNetMallDemo {
    //模拟查询商铺列表
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("pdd"),
            new NetMall("tmall"),
            new NetMall("dangdang"), new NetMall("dangdang"), new NetMall("dangdang"), new NetMall("dangdang"),
            new NetMall("tianmao"),
            new NetMall("suning"),
            new NetMall("douban")
    );
    //同步 一个一个查
    public static List<String> getPriceByStep(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> String.format(productName + " in %s price is %.2f", netMall.getMallName(), netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }
    //异步 并发查
    public static List<String> getPriceByASync(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f", netMall.getMallName(), netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //同步
        List<String> listPrice1 = getPriceByStep(list, "mysql");
        for (String price : listPrice1) {
            System.out.println(price);
        }
        long end = System.currentTimeMillis();
        System.out.println("costTime: "+(end - start)+"毫秒");
        System.out.println("-------------------------");
        long start2 = System.currentTimeMillis();
        //异步
        List<String> listPrice2 = getPriceByASync(list, "mysql");
        for (String price : listPrice2) {
            System.out.println(price);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("costTime: "+(end2 - start2)+"毫秒");
    }
}

class NetMall {
    @Getter
    private String mallName;

    public NetMall(String mallName) {
        this.mallName = mallName;
    }

    //模拟查询数据库获得商品价格
    public double calcPrice(String name) {
        //阻塞1秒 模拟查询数据库
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //生成0-1之间的一个随机数*2 + 查询书籍名称第一个字符    。得到的是ASCII对应的数值
        double price = ThreadLocalRandom.current().nextDouble() * 2 + name.charAt(0);
        //返回价格
        return price;
    }
}