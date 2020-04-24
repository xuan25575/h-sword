package com.hxy.jdk.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FlatMapTest {
    public static void main(String[] args) {

        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1,2,5), Arrays.asList(1,3, 4, 5));
        stream.flatMap(list -> list.stream()).distinct()
                .forEach(i -> System.out.println(i));

//        stream.forEach(System.out::println);
    }
}
