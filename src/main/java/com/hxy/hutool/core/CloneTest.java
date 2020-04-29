package com.hxy.hutool.core;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
import cn.hutool.core.util.ObjectUtil;

/**
 * @Author huang_2
 * @Date 2020/4/28 11:20 下午
 * @Description clone 类处理。
 */
public class CloneTest  {


    public static void main(String[] args) {


        Cat cat = new Cat();
        Cat clone = cat.clone();
        System.out.println(cat == clone);

        Dog dog = new Dog();
        Dog clone1 = dog.clone();

        System.out.println(dog.name == clone1.name);

        // cat 没有序列化，返回null
        Cat clone2 = ObjectUtil.cloneByStream(cat);
        System.out.println(clone2);


    }

    /**
     * 猫猫类，使用实现Cloneable方式
     */
    private static class Cat implements Cloneable<Cat>{
        private String name = "miaomiao";
        private int age = 2;

        @Override
        public Cat clone() {
            try {
                return (Cat) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new CloneRuntimeException(e);
            }
        }
    }

    /**
     * 狗狗类，用于继承CloneSupport类
     *
     */
    private static class Dog extends CloneSupport<Dog> {
        private String name = "wangwang";
        private int age = 3;
    }


}


