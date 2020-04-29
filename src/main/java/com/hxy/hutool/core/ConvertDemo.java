package com.hxy.hutool.core;

import cn.hutool.core.convert.Convert;
import com.hxy.jdk.stream.domain.Person;
import lombok.Data;

/**
 * @Author huang_2
 * @Date 2020/4/29 10:28 上午
 * @Description TODO
 */
public class ConvertDemo {


    public static void main(String[] args) {

        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);

        long[] b = {1,2,3,4,5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);

        System.out.println(aStr+" "+bStr);

        String convert = Convert.convert(String.class, "2");
        System.out.println(convert);

        Person p  = new Person();
        p.setName("zhansan ");
        p.setAddress("dd");

        Person p2  = new Person().setAddress("cc").setName("lisi");
        P convert1 = Convert.convert(P.class, p);

        System.out.println(convert1);


    }


    @Data
    class  P {
        private String name;

        private int age;

        private String address;
    }
}
