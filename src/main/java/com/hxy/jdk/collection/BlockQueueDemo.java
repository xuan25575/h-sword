package com.hxy.jdk.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author huang_2
 * @Date 2020/4/29 9:15 下午
 * @Description TODO
 */
public class BlockQueueDemo {

    public static void main(String[] args) {
        BlockingQueue queue  = new ArrayBlockingQueue(10);

        queue.add(1);
        queue.add(2);

        System.out.println(queue.remove(0));

    }
}
