package com.hxy.quartz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author huang_2
 * @Date 2020/4/24 9:22 下午
 * @Description
 * https://blog.csdn.net/sinat_34104446/article/details/
 * 83345960?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4#%E4%B8%89%E3%80%81%E9%97%AE%E9%A2%98%E8%A7%A3%E5%86%B3
 */
public class TestJob {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public void execute() {
        try {
            log.info("定时任务执行中.......");
            System.out.println("***********定时任务执行：" + Thread.currentThread().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}