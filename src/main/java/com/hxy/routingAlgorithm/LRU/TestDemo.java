package com.hxy.routingAlgorithm.LRU;

import org.junit.Test;

import java.util.Map;

/**
 * @Author huang_2
 * @Date 2020/5/2 7:33 下午
 * @Description TODO
 */
public class TestDemo {


   @Test
    public void put() throws Exception {
        LRULinkedMap<String,Integer> map = new LRULinkedMap(3) ;
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);

        for (Map.Entry<String, Integer> e : map.getAll()){
            System.out.print(e.getKey() + " : " + e.getValue() + "\t");
        }

        System.out.println("");
        map.put("4",4);
        for (Map.Entry<String, Integer> e : map.getAll()){
            System.out.print(e.getKey() + " : " + e.getValue() + "\t");
        }
    }

    @Test
    public void put2() throws Exception {
        LRUMap<String,Integer> lruMap = new LRUMap(3) ;
        lruMap.put("1",1) ;
        lruMap.put("2",2) ;
        lruMap.put("3",3) ;

        System.out.println(lruMap.toString());

        lruMap.put("4",4) ;
        System.out.println(lruMap.toString());

        lruMap.put("5",5) ;
        System.out.println(lruMap.toString());
    }

}
