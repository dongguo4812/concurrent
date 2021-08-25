package com.dongguo.filereader;

import com.dongguo.filereader.util.Constants;
import com.dongguo.filereader.util.FileReader;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dongguo
 * @date 2021/8/23 0023 16:43
 * @description: 异步
 */
@Slf4j(topic = "c.Async")
public class Async {

    public static void main(String[] args) {
        new Thread(() -> FileReader.read(Constants.MP4_FULL_PATH)).start();
        log.debug("do other things ...");
    }

}
