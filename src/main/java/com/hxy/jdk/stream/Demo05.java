package com.hxy.jdk.stream;

import java.util.stream.Stream;

/**
 * @Description   stream demo  无限流 处理斐波纳契元组序列
 * @date 2019/8/22
 */
public class Demo05 {
    public static void main(String[] args) {
        // 输出对
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.print("(" + t[0] + "," + t[1] +")"+"  "));


        System.out.println("------------------------");
         // 输出列
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(t->System.out.print(t+"  "));

        System.out.println("----------------------------");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
