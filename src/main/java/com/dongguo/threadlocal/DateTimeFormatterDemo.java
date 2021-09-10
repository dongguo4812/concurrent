package com.dongguo.threadlocal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Dongguo
 * @date 2021/9/8 0008-9:23
 * @description:
 */
class DateUtils {
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime parse(String dateString) {
        return LocalDateTime.parse(dateString, dtf);
    }
}

public class DateTimeFormatterDemo {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                LocalDateTime time = DateUtils.parse("2021-09-08 08:00:00");
                System.out.println(time);
            }, "t" + i).start();
        }
    }
}
