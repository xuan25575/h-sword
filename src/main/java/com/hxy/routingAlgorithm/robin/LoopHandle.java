package com.hxy.routingAlgorithm.robin;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author huang_2
 * @Date 2020/5/2 8:21 下午
 * @Description
 * 请求 对服务器信息取模，返回一个服务器信息
 */
public class LoopHandle {

    private AtomicLong atomicLong = new AtomicLong();


    /**
     * 自旋 + CAS 并发性高的话
     * @param size
     * @return
     */
    private Long incrementAndGetModulo(int size){
        //自旋 + CAS
        for(;;){
            long current = atomicLong.get();
            long next = (current + 1) % size;
            if (atomicLong.compareAndSet(current, next))
                return next;
        }
    }

    /**
     *
     * @param list 服务器列表
     * @return 返回一个 节点信息
     */
    public String handle(List<String> list){
        Long aLong = incrementAndGetModulo(list.size());
        return list.get(aLong.intValue());
    }


    /**
     * 简单处理
     * @param list
     * @return
     */
    public String handle2(List<String> list){
        Long aLong = atomicLong.incrementAndGet()%list.size();
        return list.get(aLong.intValue());
    }


}
