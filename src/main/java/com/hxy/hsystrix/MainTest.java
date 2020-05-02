package com.hxy.hsystrix;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author huang_2
 * @Date 2020/4/29 9:51 下午
 * @Description TODO
 */
@Slf4j
public class MainTest {
    public static void main(String[] args) throws Exception {
        CommandOrder commandPhone = new CommandOrder("手机");
        CommandOrder command = new CommandOrder("电视");
//        CommandOrder command2 = new CommandOrder("电视");


        //阻塞方式执行
        String execute = commandPhone.execute();
        log.info("execute=[{}]", execute);

        //异步非阻塞方式
        Future<String> queue = command.queue();
        String value = queue.get(200, TimeUnit.MILLISECONDS);
        log.info("value=[{}]", value);

//        String execute2 = command2.execute();
//        log.info("execute=[{}]", execute2);


        CommandUser commandUser = new CommandUser("张三");
        String name = commandUser.execute();
        log.info("name=[{}]", name);
    }
}
