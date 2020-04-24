package com.hxy.jdk.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Description stream demo reduce方法
 * @date 2019/8/22
 */
public class Demo03 {
    public static void main(String[] args) {
        // 求和
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        int sum = numbers.stream().reduce(0, Integer::sum);
        int product = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        System.out.println(product);
        // 最大值和最小值
        Integer maxInt = numbers.stream().reduce(Integer::max).get();
        Integer minInt = numbers.stream().reduce(Integer::min).get();
        System.out.println(maxInt);
        System.out.println(minInt);


        // 怎样用 map 和 reduce 方法数一数流中有多少个菜呢？
        // 如果 菜看做一个对象
        List<String> stringList = Arrays.asList(new String("1"), new String("2"), new String("3"));
        int count = stringList.stream()
                .map(d -> 1) // 将对象映射 数字
                .reduce(0, (a, b) -> a + b);
        long count2 = stringList.stream().count();

        System.out.println(count);
        System.out.println(count2);

    }
}
