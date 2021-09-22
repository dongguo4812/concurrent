package com.dongguo.sync;

import java.util.Hashtable;

/**
 * @author Dongguo
 * @date 2021/9/11 0011-15:05
 * @description:
 */
public class UnSafeDemo2 {

    public static void main(String[] args) {
        putValue("v1");
    }

    public static void putValue(String value) {
        Hashtable table = new Hashtable();
        // 线程1，线程2
        if (table.get("key") == null) {
            table.put("key", value);
        }
    }
}