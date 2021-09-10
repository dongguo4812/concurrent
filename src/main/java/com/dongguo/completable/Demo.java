package com.dongguo.completable;

/**
 * @author Dongguo
 * @date 2021/9/5 0005-12:18
 * @description:
 */
public class Demo {
    public static void main(String[] args) {
        Book book = new Book();
        //传统方法
//        book.setId(1);
//        book.setBookName("mysql");
//        book.setPrice(99.9);
//        book.setAuthor("zhangsan");
        //链式调用
        book.setId(1)
                .setBookName("mysql")
                .setPrice(99.9)
                .setAuthor("zhangsan");
    }
}