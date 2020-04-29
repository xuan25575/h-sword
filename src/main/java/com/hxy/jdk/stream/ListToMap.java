package com.hxy.jdk.stream;


import com.hxy.jdk.stream.domain.Person;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListToMap {



    public static void main(String[] args) {

    }


    //有时候，希望得到的map的值不是对象，而是对象的某个属性，那么可以用下面的方式：
    public Map<Long, String> getIdNameMap(List<Person>  personList) {
        return personList.stream().collect(Collectors.toMap(Person::getId, Person::getName));
    }

    public Map<Long, Person> getIdAccountMap(List<Person> personList) {
        return personList.stream().collect(Collectors.toMap(Person::getId, person -> person));
    }

    public Map<Long, Person> getIdAccountMap2(List<Person> personList) {
        return personList.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
    }

    // 重复key的情况
    public Map<String, Person> getNameAccountMap(List<Person> personList) {
        return personList.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
    }


    /**
     * 这里只是简单的使用后者覆盖前者来解决key重复问题。
     * @param personList
     * @return
     */
    public Map<String, Person> getNameAccountMap3(List<Person> personList) {
        return personList.stream().collect(Collectors.toMap(Person::getName, Function.identity(), (key1, key2) -> key2));
    }


    /**
     * toMap还有另一个重载方法，可以指定一个Map的具体实现，来收集数据：
     * @param personList
     * @return
     */
    public Map<String, Person> getNameAccountMap2(List<Person> personList) {
        return personList.stream().collect(Collectors.toMap(Person::getName, Function.identity(), (key1, key2) -> key2, LinkedHashMap::new));
    }


}
