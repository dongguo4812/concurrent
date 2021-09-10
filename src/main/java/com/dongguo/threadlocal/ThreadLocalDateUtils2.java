package com.dongguo.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dongguo
 * @date 2021/9/8 0008-8:33
 * @description:
 */
public class ThreadLocalDateUtils2 {
    public static void main(String[] args) throws ParseException {

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = sdf.parse("2021-09-08 08:00:00");
                    sdf = null;//手动设为null，方法结束要作为垃圾回收
                    System.out.println(date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}