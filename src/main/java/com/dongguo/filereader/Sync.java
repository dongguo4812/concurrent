package com.dongguo.filereader;


import com.dongguo.filereader.util.Constants;
import com.dongguo.filereader.util.FileReader;
import lombok.extern.slf4j.Slf4j;
/**
 * @author Dongguo
 * @date 2021/8/23 0023 16:44
 * @description: 同步
 */
@Slf4j(topic = "c.Sync")
public class Sync {

    public static void main(String[] args) {
        FileReader.read(Constants.MP4_FULL_PATH);
        log.debug("do other things ...");
    }
}
