package com.dongguo.object;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author Dongguo
 * @date 2021/9/8 0008-19:09
 * @description:
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {
        Object object = new Object();
        //使用ClassLayout解析object并转成可以打印的格式
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
}