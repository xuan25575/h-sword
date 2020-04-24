package com.hxy.jdk.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author huang_2
 * @Date 2020/4/24 3:30 下午
 * @Description TODO
 */
public class ParallelSteam {

    //Parallel Streams并发的stream
    //默认情况下，它会产生与机器CPU数量相等的线程
    public static void main(String[] args) {
        Map<String, List<Integer>> numbersPerThread = IntStream.rangeClosed(1, 160)
                .parallel()
                .boxed()
                .collect(groupingBy(i -> Thread.currentThread().getName()));
        numbersPerThread.forEach((k, v) -> System.out.println(String.format("%s >> %s", k, v)));


        //另外一个会用到parallel操作的例子是，当你像下面这样要处理一个URL的列表时：
//        String[] urls = {"https://www.google.co.in/", "https://twitter.com/", "http://www.facebook.com/"};
//        Arrays.stream(urls).parallel().map(url -> getUrlContent(url)).forEach(System.out::println);
    }

    private static Object getUrlContent(String url) {
        return null;
    }

    //通过更改系统属性来控制fork-join线程池的数量
    //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2")。

    //如果你想更好的掌握什么时候应该使用并发的stream,
    // 推荐你阅读由Doug Lea和其他几位Java大牛写的文章http://gee.cs.oswego.edu/dl/html/StreamParallelGuidance.html。

}

