package com.dongguo;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author Dongguo
 * @date 2021/8/23 0023-16:35
 * @description:
 */

public class Test2 {
    private static Logger log = Logger.getLogger(Test.class);
    @Test
    public void testLog(){
        Random random = new Random();
        int i = random.nextInt(100);
        System.out.println(i);
        log.debug("debug");
        log.error("error");
    }
}