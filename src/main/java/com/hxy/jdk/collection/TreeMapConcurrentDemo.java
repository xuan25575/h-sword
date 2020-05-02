package com.hxy.jdk.collection;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author huang_2
 * @Date 2020/5/2 5:40 下午
 * @Description TODO
 */
public class TreeMapConcurrentDemo {


    /**
     *  一致性hash 实现。
     *  hash 环。
     * 1.写入数据候，TreeMap 可以保证 key 的自然排序。
     * 2.tailMap 可以获取比当前 key 大的部分数据。
     * 3.当这个方法有数据返回时取第一个就是顺时针中的第一个节点了。
     * 4.如果没有返回那就直接取整个 Map 的第一个节点，同样也实现了环形结构。
     */
    @Test
    public void add(){
        TreeMap<Long,String> treeMap = new TreeMap<>();
        treeMap.put(100L,"127.0.0.100");
        treeMap.put(10L,"127.0.0.10");
        treeMap.put(5L,"127.0.0.5");
        treeMap.put(124L,"127.0.0.124");
        SortedMap<Long, String> last = treeMap.tailMap(101L);

        if(!last.isEmpty()){
            System.out.println(last.get(last.firstKey()));
        }else{
            System.out.println(treeMap.firstEntry().getValue());
        }

    }

    public void add1(){

    }


}
