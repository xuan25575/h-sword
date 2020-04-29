package com.hxy.spring;

import com.hxy.quartz.service.TestJob;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author huang_2
 * @Date 2020/4/24 9:33 下午
 * @Description 启动Spring容器
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TestJob testJob = (TestJob) ctx.getBean("testJob");
        System.out.println(testJob);

    }



}
