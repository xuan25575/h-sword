package com.hxy.jdk.stream;

import com.hxy.jdk.stream.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author huang_2
 * @Date 2020/4/24 2:41 下午
 * @Description 集合按照多个属性分组
 */
public class GroupByDemo2 {
    public static void main(String[] args) {

        //多个属性拼接出一个组合属性
        Person user1 = new Person("zhangsan", 10,"beijing" );
        Person user2 = new Person("zhangsan", 02,"beijing");
        Person user3 = new Person("lisi", 03,"shanghai");
        List<Person> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        Map<String, List<Person>> collect = list.stream().collect(Collectors.groupingBy(e -> fetchGroupKey(e)));

        System.out.println(collect);


        //嵌套调用groupBy
        Map<String, Map<String, List<Person>>> collect2
                = list.stream().collect(
                Collectors.groupingBy(
                        Person::getAddress, Collectors.groupingBy(Person::getName)
                )
        );

        System.out.println(collect2);

        //使用Arrays.asList
        //我有一个与Web访问记录相关的域对象列表。这些域对象可以扩展到数千个。
        //我没有资源或需求将它们以原始格式存储在数据库中，因此我希望预先计算聚合并将聚合的数据放在数据库中。
        //您应该为地图创建自定义密钥

//        Function<WebRecord, List<Object>> keyExtractor = wr ->
//                Arrays.<Object>asList(wr.getFiveMinuteWindow(), wr.getCdn(), wr.getIsp(),
//                        wr.getResultCode(), wr.getTxnTime());
//
//        Map<List<Object>, Integer> aggregatedData = webRecords.stream().collect(
//                Collectors.groupingBy(keyExtractor, Collectors.summingInt(WebRecord::getReqBytes)));

    }


    private static String fetchGroupKey(Person user){

        return user.getName() +"#"+ user.getAddress();
    }
}
