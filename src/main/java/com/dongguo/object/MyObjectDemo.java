package com.dongguo.object;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Dongguo
 * @date 2021/9/8 0008-18:30
 * @description:
 */
class MyObject {
    int i = 5;
    char a = 'a';
    long l =99;
}

public class MyObjectDemo {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        //使用ClassLayout解析object并转成可以打印的格式
        System.out.println(ClassLayout.parseInstance(myObject).toPrintable());
    }
}