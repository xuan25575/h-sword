package com.hxy.guava.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author huang_2
 * @Date 2020/5/2 5:02 下午
 * @Description TODO
 */
public class BloomFilterDemo {


    /**
     *   funnel：数据类型(一般是调用Funnels工具类中的)
     *   expectedInsertions：期望插入的值的个数
     *   fpp 错误率(默认值为0.03)
     */
    @Test
    public void guavaTest() {
        long star = System.currentTimeMillis();
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                10000000,
                0.01);

        for (int i = 0; i < 10000000; i++) {
            filter.put(i);
        }

        Assert.assertTrue(filter.mightContain(1));
        Assert.assertTrue(filter.mightContain(2));
        Assert.assertTrue(filter.mightContain(3));
        Assert.assertFalse(filter.mightContain(10000000));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }


    /**
     * 当让我把数组长度缩小到了 100W 时就出现了一个误报，400230340 这个数明明没在集合里，却返回了存在。
     * 这也体现了 Bloom Filter 的误报率。
     *
     * 我们提高数组长度以及 hash 计算次数可以降低误报率，
     * 但相应的 CPU、内存的消耗就会提高；这就需要根据业务需要自行权衡。
     */
    @Test
    public void bloomFilterTest(){
        long star = System.currentTimeMillis();
//        BloomFilters bloomFilters = new BloomFilters(10000000) ;
        BloomFilters bloomFilters = new BloomFilters(10000000) ;
        for (int i = 0; i < 10000000; i++) {
            bloomFilters.add(i + "") ;
        }
        Assert.assertTrue(bloomFilters.check(1+""));
        Assert.assertTrue(bloomFilters.check(2+""));
        Assert.assertTrue(bloomFilters.check(3+""));
        Assert.assertTrue(bloomFilters.check(999999+""));
        Assert.assertFalse(bloomFilters.check(400230340+""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }
}
