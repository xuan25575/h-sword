package com.hxy.routingAlgorithm.concurrenthash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author huang_2
 * @Date 2020/5/2 6:21 下午
 * @Description TODO
 */
public class ConcurrentHash extends AbstractConsistentHash {


    // 这个集合主要存储的节点信息。
    private TreeMap<Long,String> treeMap = new TreeMap<>();

    // 虚拟节点 数量
    private static Integer VIRTUAL_NODE_SIZE =2 ;


    @Override
    protected void add(long key, String value) {
        // 添加虚拟节点信息
        for (int i = 0; i < VIRTUAL_NODE_SIZE; i++) {
            long hash = super.hash("vir-"+key+i);
            treeMap.put(hash,value);
        }
        // 真实节点信息。
        treeMap.put(key,value);

    }

    /**
     * 这个方法主要 key ， hash后 分布到哪一个节点上。
     * @param value
     * @return
     */
    @Override
    protected String getFirstNodeValue(String value) {
        // 将当前数据信息 hash
        long hash = super.hash(value);
        // 取得顺时针 下的集合
        SortedMap<Long, String> tailMap = treeMap.tailMap(hash);
        // 如果不为空， 顺时针下的第一个节点
        if(!tailMap.isEmpty()){
            return treeMap.get(tailMap.firstKey());
        }
        // 否是属于第一个节点
        return treeMap.firstEntry().getValue();
    }
}
