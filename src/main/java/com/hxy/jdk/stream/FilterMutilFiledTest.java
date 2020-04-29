package com.hxy.jdk.stream;



import com.hxy.jdk.stream.domain.Person;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterMutilFiledTest {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Tam", 16, "China"));
        list.add(new Person("Tam", 16, "China"));
        list.add(new Person("Tom", 15, "Japan"));
        list.add(new Person("Tcm", 18, "Russia"));
        list.add(new Person("Tom", 15, "America"));
        list.add(new Person("Tdm", 16, "America"));
        list.add(new Person("Tem", 17, "America"));


        ArrayList<Person> collect = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(o -> o.getName() + "#" + o.getAge() + "#" + o.getAddress()))),
                ArrayList::new));

        collect.forEach(person -> System.out.println(person.toString()));
        System.out.println("==========================");
        List<Person> collect1 = list.stream()
                .sorted(Comparator.comparing(o -> o.getName() + "#" + o.getAge() + "#" + o.getAddress()))
                .collect(Collectors.toList());
        collect1.forEach(person -> System.out.println(person.toString()));
    }

    /**
     * 多字段分组
     * 所谓多字段分组 【就是 多个字段连接到一起（成为一个字符串） 然后通过该字符串来分组。】
     * @param records
     */
    private void mutilFieldGroup(List<Person> records){
        // 分组统计
        Map<String, Long> countMap = records.stream().collect(Collectors.groupingBy(o -> o.getName() + "_" + o.getAddress(), Collectors.counting()));
        List<Person> countRecords = countMap.keySet().stream().map(key -> {

            // guava方式
          //  List<String> strings = Splitter.on("_").splitToList(key);
           // String name1 = strings.get(0);
//            String address1 = strings.get(1);

            String[] temp = key.split("_");
            String name = temp[0];
            String address = temp[1];

            Person record = new Person();
            record.setName(name);
            record.setAddress( address);
            record.setAge(countMap.get(key).intValue()); // 统计的数
            return record;
        }).collect(Collectors.toList());
    }

    /**
     * 多字段去重
     * 所谓多字段去重  【就是 多个字段连接到一起（成为一个字符串） 然后通过该字符串来去重。】
     */
    private void mutilFieldFilter(){

        ArrayList<MilestonesValidTO> list2 = new ArrayList<MilestonesValidTO>();
        list2.add(new MilestonesValidTO("1","A","sea","2341"));
        list2.add(new MilestonesValidTO("1","A","sea","2341"));
        list2.add(new MilestonesValidTO("3","B","sea","2341"));
        list2.add(new MilestonesValidTO("4","A","sea","2341"));
        list2.add(new MilestonesValidTO("5","C","sea","2341"));

        // 用到 treeset 这个集合的 特性。
        List<MilestonesValidTO> lst = list2.parallelStream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(o -> o.getId() + "_" + o.getMessage() + "_" + o.getOrderNO()))),
                ArrayList<MilestonesValidTO>::new));

        // 对 list 按照多个字段进行去重id ,  message ,orderNO 去重:用于收集,统计,比较合适
//        Map<String, Map<String, List<MilestonesValidTO>>> collect2 = list2.parallelStream()
//                .collect(Collectors.groupingBy(MilestonesValidTO::getId,Collectors.groupingBy(MilestonesValidTO::getMessage)));



        //使用map去重  stream 只能消费一次。
        List<MilestonesValidTO> unique2 = list2.stream()
                .filter(distinctByKey(o -> o.getId()))
                .collect(Collectors.toList());
        System.out.println(unique2);



        lst.forEach(m-> System.err.println(m.toString()));
    }



    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        System.out.println("这个函数将应用到每一个item");
        // putIfAbsent  如果指定的键尚未与值关联（或被映射为{@code null}），则将其与给定值关联并返回{@code null}，否则返回当前值。
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}