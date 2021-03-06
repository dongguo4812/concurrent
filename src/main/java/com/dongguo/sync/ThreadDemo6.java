package com.dongguo.sync;


import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-8:18
 * @description: 任务间使用管道进行输入输出
 */

public class ThreadDemo6 {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        //休眠4秒钟后中断
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}

class Sender implements Runnable {

    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter() {
        return out;
    }

    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c < 'Z'; c++) {
                    out.write(c);
                    //随机休眠500以内毫秒
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }
        } catch (IOException e) {
            System.out.println(e + " Sender write exception");
        } catch (InterruptedException e) {
            System.out.println(e + " Sender sleep interrupt");
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + (char) in.read() + ",");
            }
        } catch (IOException e) {
            System.out.println(e + " Receiver read exception");
        }
    }
}


