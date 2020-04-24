package com.hxy.jdk.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description steam demo
 * @date 2019/8/22
 */
public class Demo02 {
    public static void main(String[] args) {

        // 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4,
        //5]，应该返回[1, 4, 9, 16, 25]。

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list = integers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(list);


       // 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
        //该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，
        // 你可以用有两个元素的数组来代表数对。
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());

        System.out.println(pairs.get(1)[0] +":"+ pairs.get(1)[1]);


        // 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
//        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
//        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs2 =
                numbers1.stream()
                        .flatMap(i ->
                                numbers2.stream()
                                        .filter(j -> (i + j) % 3 == 0)
                                        .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());
        System.out.println(pairs2);
    }
}
