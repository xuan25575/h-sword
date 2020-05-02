package com.hxy.routingAlgorithm.Romdom;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author huang_2
 * @Date 2020/5/2 8:34 下午
 * @Description TODO
 */
public class RandomRule {


    /**
     *
     * @param list 服务器列表
     * @return 返回一个 节点信息
     */
    public String handle(List<String> list){
        int i = chooseRandomInt2(list.size());
        return list.get(i);
    }


    /**
     * 并发高的话这种
     * 如果每个线程都维护一个种子变量，则每个线程生成随机数时都根据自己老的种子计算新的种子，
     * 并使用新种子更新老的种子，再根据新种子计算随机数，就不会存在竞争问题了，这会大大提高并发性能
     * @param serverCount
     * @return
     */
    private int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    /**
     * Random的缺点是多个线程会使用同一个原子性种子变量，从而导致对原子变量更新的竞争
     * 由于原子变量的更新是CAS操作，同时只有一个线程会成功，所以会造成大量线程进行自旋重试，这会降低并发性能，
     * @param serverCount
     * @return
     */
    private int chooseRandomInt2(int serverCount) {
        Random random = new Random();
        return random.nextInt(serverCount);
    }
}
