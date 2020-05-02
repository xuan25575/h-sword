package com.hxy.jdk.thread;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Author huang_2
 * @Date 2020/4/30 4:30 下午
 * @Description TODO
 */
@Slf4j
public class PipedDemo {


    public static void main(String[] args) throws IOException {
        piped();
    }

    public static void piped() throws IOException {
        //面向于字符 PipedInputStream 面向于字节
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        //输入输出流建立连接
        writer.connect(reader);


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("running");
                try {
                    for (int i = 0; i < 10; i++) {

                        writer.write(i+"");
                        Thread.sleep(10);
                    }
                } catch (Exception e) {

                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("running2");
                int msg = 0;
                try {
                    while ((msg = reader.read()) != -1) {
                        log.info("msg={}", (char) msg);
                    }

                } catch (Exception e) {

                }
            }
        });
        t1.start();
        t2.start();
    }


}
