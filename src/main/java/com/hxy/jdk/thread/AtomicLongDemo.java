package com.hxy.jdk.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author huang_2
 * @Date 2020/5/1 8:28 下午
 * @Description TODO
 */
public class AtomicLongDemo {

    public static void main(String[] args) {
        AtomicLong atomicLong  = new AtomicLong(0L);


        System.out.println(atomicLong.incrementAndGet());

        System.out.println(atomicLong.getAndIncrement());
    }
}
