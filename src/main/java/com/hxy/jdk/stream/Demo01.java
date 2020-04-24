package com.hxy.jdk.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description Stream demo
 * @date 2019/8/22
 */
public class Demo01 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct() // 它会返回一个元素各异
                .forEach(System.out::println);


        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

      //  wordLengths.forEach(System.out::print);
        System.out.println(wordLengths);

        String[] arrayOfWords = {"Goodbye", "World"};
        words = Arrays.asList(arrayOfWords);
        // 返回值不一样.
        List<Stream<String>> collect = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println();
        List<String> uniqueCharacters =
                words.stream()
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println(uniqueCharacters);

    }
}
