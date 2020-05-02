package com.hxy.hsystrix;

import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author huang_2
 * @Date 2020/4/29 9:47 下午
 * @Description TODO
 */
public class CommandOrder extends HystrixCommand<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommandOrder.class);

    private String orderName;

    public CommandOrder(String orderName) {


        // CommandGroupKey  -- ThreadPoolKey 有ThreadPoolKey用这命名 ，没有就CommandGroupKey 命名
        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey("OrderGroup"))
                //线程分组
              //  .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("OrderPool"))

                .andCommandKey(HystrixCommandKey.Factory.asKey("orderService"))
                //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000))

                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        )
        ;
        this.orderName = orderName;
    }


    @Override
    public String run() throws Exception {

        LOGGER.info("orderName=[{}]", orderName);

        TimeUnit.MILLISECONDS.sleep(100);
        return "OrderName=" + orderName;
    }


}
