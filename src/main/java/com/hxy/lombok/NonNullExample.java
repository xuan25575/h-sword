package com.hxy.lombok;
import com.hxy.jdk.stream.domain.Person;
import lombok.NonNull;
/**
 * @Author huang_2
 * @Date 2020/4/24 8:55 下午
 * @Description 2.@NonNull
 */


public class NonNullExample {

    private String name;

    public NonNullExample(@NonNull Person person) {
        this.name = person.getName();
    }
//    翻译
//    public NonNullExample(@NonNull Person person) {
//        if (person == null) {
//            throw new NullPointerException("person");
//        }
//        this.name = person.getName();
//    }

}
