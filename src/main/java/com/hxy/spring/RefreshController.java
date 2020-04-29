package com.hxy.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author huang_2
 * @Date 2020/4/25 8:01 下午
 * @Description 手动Spring 容器
 *
 *
 * 在web应用开发中时常需要修改配置，并动态的重新加载ApplicationContext。比如，设置和切换数据库。
 * 这时，不得不重启tomcat来加载spring配置文件
 * 如果能在不重启tomcat的情况下，手动动态加载spring配置文件，动态重启读取spring配置文件，这样会十分方便。
 * spring提供了refresh刷新机制。
 */
@Controller
public class RefreshController {


    @RequestMapping(value="refresh")
    @ResponseBody
    public void refresh(HttpServletRequest request){

	    /*XmlWebApplicationContext context1 = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext((request.getSession().getServletContext()));
        context1.refresh();*/
        /**
         * Interface to provide configuration for a web application. This is read-only while
         * the application is running, but may be reloaded if the implementation supports this.
         * 用于为Web应用程序提供配置的接口。在应用程序运行时，这是只读的，但如果实现支持此操作，则可以重新加载应用程序。
         */
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        /**
         * Central interface to provide configuration for an application.
         * This is read-only while the application is running, but may be
         * reloaded if the implementation supports this.
         *
         * 为应用程序提供配置的中央接口。在应用程序运行时，这是只读的，但如果实现支持此操作，则可能被重新加载。
         */
        ApplicationContext parent = context.getParent();
        if (parent !=null) {
            ((AbstractRefreshableApplicationContext) parent) .refresh();
        }
        ((AbstractRefreshableApplicationContext) context).refresh();
    }
}
