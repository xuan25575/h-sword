package com.hxy.routingAlgorithm.concurrenthash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huang_2
 * @Date 2020/5/2 7:16 下午
 * @Description
 *  concurrentHash  简单使用
 */
public class Main {


    @Test
    public void getFirstNodeValue(){
        AbstractConsistentHash map = new ConcurrentHash();
        List<String> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add("127.0.0."+i);
        }
        String s = map.process(strings, "lisi");
        System.out.println(s);
    }
}
