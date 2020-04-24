package com.hxy.jdk.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description stream demo  数值流  勾股数
 * @date 2019/8/22
 */
public class Demo04 {
    //Math.sqrt(a*a + b*b) % 1 == 0   开根号的数不能有小数，对1取模
    public static void main(String[] args) {
        // 返回一个三元数流。
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );

        pythagoreanTriples.limit(5)
                .forEach(t-> System.out.println(t[0]+"，"+t[1]+","+t[2]));

        // -------------------------------------------
        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                        .filter(t -> t[2] % 1 == 0)); // 元组中的第三个元素必须是整数

        pythagoreanTriples2.limit(5)
                .forEach(t-> System.out.println(t[0]+"，"+t[1]+","+t[2]));
    }

}
