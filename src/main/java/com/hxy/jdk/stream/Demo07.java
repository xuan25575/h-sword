package com.hxy.jdk.stream;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description TODO
 * @date 2019/10/15 16:16
 */
public class Demo07 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("是");
        strings.add("否");
        boolean allMatch = strings.stream().anyMatch(t -> t.equals("否"));
        System.out.println(allMatch);

        System.out.println(checkChineseChar("23汉字"));
        Demo07 demo07  = new Demo07();
        System.out.println(demo07.getClass().getName().replace('.', '/') + ".xml");
    }

    /**
     * 判断字符是否是汉字
     * @param str 字符
     * @return boolean
     */
    public static boolean checkChineseChar(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
