package com.hxy.lombok;

import java.util.ArrayList;
import java.util.HashMap;
import lombok.val;
/**
 * @Author huang_2
 * @Date 2020/4/24 8:54 下午
 * @Description 1.@val @var
 */


public class ValExample {
    public String example() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        return foo.toLowerCase();
    }

    public static void example2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        example2();
    }
}
