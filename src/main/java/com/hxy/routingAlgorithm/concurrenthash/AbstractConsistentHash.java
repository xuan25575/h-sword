package com.hxy.routingAlgorithm.concurrenthash;

import java.util.List;

/**
 * @Author huang_2
 * @Date 2020/5/2 6:24 下午
 * @Description TODO
 */
public abstract class AbstractConsistentHash {


    /**
     * 添加一个节点
     * @param key
     * @param value
     */
    protected  abstract void add(long key,String value);


    protected  void sort(){};


    /**
     * 根据当前 的key 通过一致性hash算法 取出一个节点
     * @param value
     * @return
     */
    protected abstract String getFirstNodeValue(String value);


    /**
     * 传入节点列表 和 将通过客户端信息返回一个服务节点。
     * @param strings
     * @param key
     * @return
     */
    public String process(List<String> strings, String key){

        //初始化节点信息 ，将节点信息放置到hash 环上。
        for (String string : strings) {
            add(hash(string),string);
        }
        sort();
        return getFirstNodeValue(key);

    }


    /**
     *
     * 哈希算法， 有更好实现。
     * @param value
     * @return
     */
    public Long hash(String value){

        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < value.length(); i++) {
            hash = (hash ^ value.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Long.valueOf(Math.abs(hash));

    }


}
