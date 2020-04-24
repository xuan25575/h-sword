package com.hxy.jdk.stream;


import java.util.stream.Stream;

public class ReduceTest {
    public static void main(String[] args) {

        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream.reduce(0,  // 初始值　// (1)
        (sum, str) -> sum+str.length(), // 累加器 // (2)
                (a, b) -> a+b);// 部分和拼接器，并行执行时才会用到 // (3)
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);


//        Optional<Integer> max = new ArrayList(1,22,4,5,6).stream()
//                .reduce(Integer::max);
//        if(max.isPresent()){
//            System.out.println(max.get());
//        }

    }


}
