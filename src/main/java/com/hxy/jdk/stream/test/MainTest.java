package com.hxy.jdk.stream.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @date 2019/8/22
 */
public class MainTest {

    private  static List<Transaction> transactions  = null;
    static {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    public static void main(String[] args) {

        // 找出2011年发生的所有交易，并按交易额排序（从低到高）
        List<Transaction> tr2011 = transactions.stream().filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(tr2011);

       // 交易员都在哪些不同的城市工作过
        transactions.stream().forEach(
                transaction -> {
                    String city = transaction.getTrader().getCity();
                    System.out.println(transaction.getTrader().getName()+" work in "+city);
                }
        );

        // 方式二
        List<String> citytList = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(citytList);


        // 查找所有来自于剑桥的交易员，并按姓名排序。

        List<Transaction> transactionList = transactions.stream().filter(
                transaction -> transaction.getTrader().getCity() == "Cambridge")
                .distinct()
                .sorted(Comparator.comparing(t -> t.getTrader().getName()))
                .collect(Collectors.toList());
        System.out.println(transactionList);

        // 返回所有交易员的姓名字符串，按字母顺序排序。
        // 方式一
        String traderStr = transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        // 方式二
        String string = transactions.stream()
                .map(p -> p.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());

        System.out.println(string);

        // 有没有交易员是在米兰工作的？
        boolean match = transactions.stream().anyMatch(t ->"Milan".equals(t.getTrader().getCity()));
        System.out.println(match);

        // 打印生活在剑桥的交易员的所有交易额。
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

//        int sum = transactions.stream()
//                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
//                .mapToInt(Transaction::getValue)
//                .sum();
//        System.out.println(sum);

        // 所有交易中，最高的交易额是多少？
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(max.get());

        // 找到交易额最小的交易。
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println(min.get());

        // 方式二
        Optional<Transaction> min1 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(min1.get());

    }
}
