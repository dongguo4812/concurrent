package com.dongguo.filereader.util;

import com.dongguo.filereader.Async;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {
    private static Logger log = Logger.getLogger(FileReader.class);
    public static void read(String filename) {
        int idx = filename.lastIndexOf(File.separator);
        String shortName = filename.substring(idx + 1);
        try (FileInputStream in = new FileInputStream(filename)) {
            long start = System.currentTimeMillis();
            log.debug("read [{}] start ..."+shortName);
            byte[] buf = new byte[1024];
            int n = -1;
            do {
                n = in.read(buf);
            } while (n != -1);
            long end = System.currentTimeMillis();
            log.debug("read [{}] end ... cost: {} ms"+shortName+(end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}