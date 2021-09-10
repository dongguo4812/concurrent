package com.dongguo.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dongguo
 * @date 2021/9/8 0008-8:33
 * @description:
 */
class SimpleDateFormatUtil {

    public static final ThreadLocal<SimpleDateFormat> sdfThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static Date parseToDateByThreadLocal(String stringDate) throws ParseException {
        return sdfThreadLocal.get().parse(stringDate);
    }
}
public class ThreadLocalDateUtils {
    public static void main(String[] args) throws ParseException {
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    Date date = SimpleDateFormatUtil.parseToDateByThreadLocal("2021-09-08 08:00:00");
                    System.out.println(date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }finally {
                    SimpleDateFormatUtil.sdfThreadLocal.remove();
                }
            }, "t" + i).start();
        }
    }
}