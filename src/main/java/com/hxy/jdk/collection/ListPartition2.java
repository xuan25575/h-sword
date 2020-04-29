package com.hxy.jdk.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huang_2
 * @Date 2020/4/28 10:31 下午
 * @Description TODO
 */
public class ListPartition2 {

    /**
     * 将list按照指定元素个数(n)分割
     * @param source
     * @param n      每次分割的个数
     * @return java.util.List<java.util.List       <       T>>
     * @Title: 将list按照指定元素个数(n)分割
     * @methodName: partList
     * @Description: 如果指定元素个数(n)>list.size(),则返回list;这时候商:0；余数:list.size()
     */
    public static <T> List<List<T>> partList(List<T> source, int n) {

        if (source == null) {
            return null;
        }
        if (n == 0) {
            return null;
        }
        List<List<T>> result = new ArrayList<List<T>>();
        // 集合长度
        int size = source.size();
        // 余数
        int remaider = size % n;
        System.out.println("余数:" + remaider);
        // 商
        int number = size / n;
        System.out.println("商:" + number);

        for (int i = 0; i < number; i++) {
            // 包括头 不包括尾部
            List<T> value = source.subList(i * n, (i + 1) * n);
            result.add(value);
        }

        if (remaider > 0) {
            List<T> subList = source.subList(size - remaider, size);
            result.add(subList);
        }
        return result;
    }

    /**
     * @Title: 将一个list均分成n个list,主要通过偏移量来实现的
     * @methodName: averageList
     * @param source
     * @param n 等分个数
     * @return java.util.List<java.util.List < T>>
     * @Description:
     */
    public static <T> List<List<T>> averageList(List<T> source,int n){

        if (source == null) {
            return null;
        }

        if (n == 0) {
            return null;
        }
        List<List<T>> result = new ArrayList<List<T>>();
        // 集合长度
        int size = source.size();
        // 余数
        int remaider = size % n;
        System.out.println("余数:" + remaider);
        // 商
        int number = size / n;
        System.out.println("商:" + number);

        int offset=0;//偏移量
        for(int i=0;i<n;i++){
            List<T> value=null;
            if(remaider>0){
                value=source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }

    public static void main(String[] args) {


        test2();



    }


    private static void test2(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        List<List<String>> lists = averageList(list, 9);
        System.out.println("【集合（9）等分】：" + lists); // 【集合（9）等分】：[[1], [2], [3], [4], [5], [6], [7], [8], []]

        List<List<String>> lists2 = averageList(list, 2);
        System.out.println("【集合（2）等分】：" + lists2); // 【集合（2）等分】：[[1, 2, 3, 4], [5, 6, 7, 8]]

        List<List<String>> lists3 = averageList(list, 3);
        System.out.println("【集合（3）等分】：" + lists3); // 【集合（3）等分】：[[1, 2, 3], [4, 5, 6], [7, 8]]

    }

    private static void test(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        List<List<String>> lists = partList(list, 9);
        System.out.println("【指定元素个数（9）等分】：" + lists); // 【指定元素个数（9）等分】：[[1, 2, 3, 4, 5, 6, 7, 8]]

        List<List<String>> lists2 = partList(list, 2);
        System.out.println("【指定元素个数（2）等分】：" + lists2); // 【指定元素个数（2）等分】：[[1, 2], [3, 4], [5, 6], [7, 8]]

        List<List<String>> lists3 = partList(list, 3);
        System.out.println("【指定元素个数（3）等分】：" + lists3); // 【指定元素个数（3）等分】：[[1, 2, 3], [4, 5, 6], [7, 8]]

    }
}
