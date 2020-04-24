package com.hxy.jdk.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @Description stream demo  处理划分质数和非质数
 * @date 2019/8/22
 */
public class Demo06 {

    public static  boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)  // candidate 不包括.
                .noneMatch(i -> candidate % i == 0);
    }

    // 优化版本.
    public static  boolean isPrime2(int candidate) {
        int candidateRoot = (int)Math.sqrt((double) candidate);
        return IntStream.range(2, candidateRoot)  // candidate 不包括.
                .noneMatch(i -> candidate % i == 0);
    }


    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(candidate -> isPrime(candidate)));
    }
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> listMap = partitionPrimes(90);

        System.out.println(listMap);

        //System.out.println(isPrime(7));
       // System.out.println(isPrime2(7));
    }
}
