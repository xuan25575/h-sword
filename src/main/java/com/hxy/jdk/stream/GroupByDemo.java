package com.hxy.jdk.stream;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author huang_2
 * @Date 2020/4/24 2:35 下午
 * @Description 利用 groupingBy 进行多字段分组求和
 * 对集合按照单个属性分组、分组计数、排序
 */
public class GroupByDemo {


    public static void main(String[] args) {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        // 分组
        Map<String, List<String>> result1 = items.stream().collect(
                Collectors.groupingBy(
                        Function.identity()
                )
        );
        //{papaya=[papaya], orange=[orange], banana=[banana, banana], apple=[apple, apple, apple]}
        System.out.println(result1);


        // 分组计数
        Map<String, Long> result2 = items.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );
        // {papaya=1, orange=1, banana=2, apple=3}
        System.out.println(result2);
        Map<String, Long> finalMap = new LinkedHashMap<>();


        //分组, 计数和排序
        //forEachOrdered 按照已经定义的方式遍历
        result2.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        // {apple=3, banana=2, papaya=1, orange=1}
        System.out.println(finalMap);
    }
}
