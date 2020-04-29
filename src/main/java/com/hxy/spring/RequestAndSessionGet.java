package com.hxy.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author huang_2
 * @Date 2020/4/25 7:02 下午
 * @Description
 *    如果web.xml
 *     <listener>
 *         <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
 *   </listener>
 *     能 通过 @Autowired注入request 和session
 */
@Slf4j
public class RequestAndSessionGet {

    @Autowired
    private static  HttpSession session;

    @Autowired
    private static HttpServletRequest request;


    public static void main(String[] args) {

        // 得启动web 容器
    }


    private void get(){


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();





    }




}
