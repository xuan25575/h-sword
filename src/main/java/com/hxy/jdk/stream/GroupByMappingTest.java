package com.hxy.jdk.stream;


import com.hxy.jdk.stream.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByMappingTest {

    public static void main(String[] args) {
        List<Person> list =  new ArrayList<>();
        list.add(new Person("Tam", 16, "China"));
        list.add(new Person("Tam", 16, "China"));
        list.add(new Person("Tom", 15, "Japan"));
        list.add(new Person("Tcm", 18, "Russia"));
        list.add(new Person("Tom", 15, "America"));
        list.add(new Person("Tdm", 16, "America"));
        list.add(new Person("Tem", 17, "America"));

        // 先分组 收集每个组的名字到list 中。
        Map<String, List<String>> collect2 = list.stream()
                .collect(Collectors.groupingBy(Person::getAddress,
                        Collectors.mapping(Person::getName,
                                Collectors.toList())));

        collect2.forEach((k,v)->{
            System.out.println("k = "+k +" v :"+v);
        });
    }
}
