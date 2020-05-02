package com.hxy.guava.callback;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author huang_2
 * @Date 2020/5/1 7:51 下午
 * @Description
 * Netty 就使用了这样的设计。
 * 事件回调
 * Caller 向 Notifier 提问。
 * 提问方式是异步，接着做其他事情。
 * Notifier 收到问题执行计算然后回调 Caller 告知结果
 * 思路：
 * caller 是事件源， CallBackListener 监听者
 * caller 注册监听者。
 * caller 上面也有事件 （Notifier）
 * caller.call 异步线程回调 Notifier
 * Notifier 触发事件监听 ，从而达到另一种回调的效果。
 *
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Notifier notifier = new Notifier() ;

        Caller caller = new Caller() ;
        caller.setNotifier(notifier) ;
        caller.setQuestion("你在哪儿！");
        caller.setCallBackListener(new CallBackListener() {
            @Override
            public void callBackNotify(String msg) {
                log.info("回复=【{}】" ,msg);
            }
        });

        caller.call();
    }
}
