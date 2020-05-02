package com.hxy.guava.cache;

/**
 * @Author huang_2
 * @Date 2020/5/1 8:26 下午
 * @Description TODO
 *
 *  * 需求
 *  * 从 Kafka 实时读取出应用系统的日志信息，该日志信息包含了应用的健康状况。
 *  * 如果在时间窗口 N 内发生了 X 次异常信息，相应的我就需要作出反馈（报警、记录日志等）。
 *  *
 *  * 对此 Guava 的 Cache 就非常适合，
 *  * 我利用了它的 N 个时间内不写入数据时缓存就清空的特点，在每次读取数据时判断异常信息是否大于 X 即可。
 */
public class CacheLoaderDemo {


//    @Value("${alert.in.time:2}")
//    private int time ;
//
//    @Bean
//    public LoadingCache buildCache(){
//        return CacheBuilder.newBuilder()
//                .expireAfterWrite(time, TimeUnit.MINUTES)
//                .build(new CacheLoader<Long, AtomicLong>() {
//                    @Override
//                    public AtomicLong load(Long key) throws Exception {
//                        return new AtomicLong(0);
//                    }
//                });
//    }
//
//
//
//    /**
//     * 判断是否需要报警
//     */
//    public void checkAlert() {
//        try {
//            if (counter.get(KEY).incrementAndGet() >= limit) {
//                LOGGER.info("***********报警***********");
//
//                //将缓存清空
//                counter.get(KEY).getAndSet(0L);
//            }
//        } catch (ExecutionException e) {
//            LOGGER.error("Exception", e);
//        }
//    }

}
