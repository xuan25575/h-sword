package com.hxy.hsystrix;

import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author huang_2
 * @Date 2020/4/29 9:49 下午
 * @Description TODO
 */
public class CommandUser extends HystrixCommand<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommandUser.class);

    private String userName;

    public CommandUser(String userName) {


        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserPool"))

                //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000))

                //线程池隔离
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        )
        ;
        this.userName = userName;
    }


    @Override
    public String run() throws Exception {

        LOGGER.info("userName=[{}]", userName);

        TimeUnit.MILLISECONDS.sleep(100);
        return "userName=" + userName;
    }
}
