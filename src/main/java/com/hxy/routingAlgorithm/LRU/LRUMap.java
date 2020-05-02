package com.hxy.routingAlgorithm.LRU;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author huang_2
 * @Date 2020/5/2 7:35 下午
 * @Description
 *
 * LRU
 * 以下是基于 双向链表 + HashMap 的 LRU 算法实现，对算法的解释如下:
 * 1.访问某个节点时，将其从原来的位置删除，并重新插入到链表头部。
 *   这样就能保证链表尾部存储的就是最近最 久未使用的节点，当节点数量大于缓存最大空间时就淘汰链表尾部的节点。
 * 2.为了使删除操作时间复杂度为 O(1)，就不能采用遍历的方式找到某个节点。
 *   HashMap 存储着 Key 到节点的映 射，通过 Key 就能以 O(1) 的时间得到节点，
 *   然后再以 O(1) 的时间将其从双向队列中删除。
 */
public class LRUMap<K, V> implements Iterable<K>{
    private final Map<K,Node> cacheMap = new HashMap<>();

    /**
     * 最大缓存大小
     */
    private int cacheSize;

    /**
     * 头结点
     */
    private Node<K, V> head;

    /**
     * 尾结点
     */
    private Node<K, V> tail;

    //构造方法初始化
    public LRUMap(int cacheSize) {
        this.cacheSize = cacheSize;
        head = new Node<>(); // 哨兵 --不变的
        tail = new Node<>();// 哨兵 -- 不变的
        // 初始化链表
        head.next = tail;
        tail.pre = head;


    }

    public void put(K key, V value) {
        if(cacheMap.containsKey(key)){
            Node node = cacheMap.get(key);
            unlink(node);
        }
        Node node = new Node(key,value);
        cacheMap.put(key, node);
        moveToHead(node);

        if(cacheMap.size()>cacheSize){
            Node<K, V> removeNode = removeTail();
            cacheMap.remove(removeNode.key);
        }

    }

    public V get(K key){
       return getNode(key).value;
    }

    /**
     * 移动节点 到头部
     * head 节点是哨兵
     * @param node
     */
    private void moveToHead(Node<K,V> node){
        Node oldHead = head.next;
        node.next = oldHead;
        node.pre = head;
        head.next = node;
        oldHead.pre = node;
    }


    /**
     * 通过key 获取一个节点
     * @param key
     * @return
     */
    private Node<K,V> getNode(K key){
        if(!cacheMap.containsKey(key)){
            return null;
        }
        Node node = cacheMap.get(key);
        unlink(node); // 释放节点
        moveToHead(node); // 移动到头部
        return node;
    }


    /**
     * 删除尾节点
     */
    private Node<K,V> removeTail() {
        // 要删除的节点
        Node node = tail.pre;
        Node newTail = node.pre;
        newTail.next = tail;
        tail.pre = newTail;

        node.pre = null;
        node.next = null;

        return node;
    }

    /**
     * 释放一个节点 断开连接即可
     * @param node
     */
    private void unlink(Node node) {

        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;
        // help gc
        node.pre = null;
        node.next = null;
    }




    private class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private Node<K,V> cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                Node<K,V> node = cur;
                cur = cur.next;
                return node.key;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        Node<K,V> node = head.next;
        while (node != tail){
            sb.append(node.key).append(":")
                    .append(node.value)
                    .append("-->") ;

            node = node.next ;
        }

        return sb.toString();
    }
}