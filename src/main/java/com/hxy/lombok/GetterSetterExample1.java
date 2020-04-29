package com.hxy.lombok;

/**
 * @Author huang_2
 * @Date 2020/4/24 8:53 下午
 * @Description TODO
 */
public class GetterSetterExample1
{
    private int age = 10;

    private String name = "张三丰";
    private boolean registerd;
    private String sex;

    public int getAge()
    {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRegisterd() {
        return this.registerd;
    }

    public String getSex() {
        return this.sex;
    }

    public GetterSetterExample1 setAge(int age) {
        this.age = age;
        return this;
    }

    public GetterSetterExample1 setName(String name) {
        this.name = name;
        return this;
    }

    public GetterSetterExample1 setRegisterd(boolean registerd) {
        this.registerd = registerd;
        return this;
    }

    public GetterSetterExample1 setSex(String sex) {
        this.sex = sex;
        return this;
    }
}
