package com.hxy.jdk.stream;

import cn.ccu.stream.domain.Task;
import cn.ccu.stream.domain.TaskType;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @Author huang_2
 * @Date 2020/4/24 3:10 下午
 * @Description TODO
 */
public class TaskSteamDemo {

    static List<Task> tasks;

    static {
        Task task1 = new Task("Read Version Control with Git book", TaskType.READING, LocalDate.of(2015, Month.JULY, 1)).addTag("git").addTag("reading").addTag("books");
        Task task2 = new Task("Read Java 8 Lambdas book", TaskType.READING, LocalDate.of(2015, Month.JULY, 2)).addTag("java8").addTag("reading").addTag("books");
        Task task3 = new Task("Write a mobile application to store my tasks", TaskType.CODING, LocalDate.of(2015, Month.JULY, 3)).addTag("coding").addTag("mobile");
        Task task4 = new Task("Write a blog on Java 8 Streams", TaskType.BLOGGING, LocalDate.of(2015, Month.JULY, 4)).addTag("blogging").addTag("writing").addTag("streams");
        Task task5 = new Task("Read Domain Driven Design book", TaskType.READING, LocalDate.of(2015, Month.JULY, 5)).addTag("ddd").addTag("books").addTag("reading");
        tasks = Arrays.asList(task1, task2, task3, task4, task5);
    }

    public static void main(String[] args) {
        System.out.println(allReadingTasks(tasks));
        System.out.println(allDistinctTags(tasks));
        System.out.println(joinAllTaskTitles(tasks));
        System.out.println();
        easyOperation();
    }

   //Example 1: 找出所有READING Task的标题，并按照它们的创建时间排序。
   private static List<String> allReadingTasks(List<Task> tasks) {
       List<String> readingTaskTitles = tasks.stream().
               filter(task -> task.getType() == TaskType.READING).
               sorted((t1, t2) -> t1.getCreatedOn().compareTo(t2.getCreatedOn())).
               map(task -> task.getTitle()).
               collect(toList());
       return readingTaskTitles;
   }


   //Example 2: 去除重复的tasks
   public List<Task> allDistinctTasks(List<Task> tasks) {
       return tasks.stream().distinct().collect(toList());
   }

   //Example 3: 根据创建时间排序，找出前5个处于reading状态的task
   public List<String> topN(List<Task> tasks, int n){

        // 记录
       int page = 0;
       // page starts from 0. So to view a second page `page` will be 1 and n will be 5.//page从0开始，所以要查看第二页的话,`page`应该为1，n应该为5
       List<String> readingTaskTitles = tasks.stream().
               filter(task -> task.getType() == TaskType.READING).
               sorted(comparing(Task::getCreatedOn).reversed()).
               map(Task::getTitle).
               skip(page * n).
               limit(n).
               collect(toList());


       return tasks.stream().
               filter(task -> task.getType() == TaskType.READING).
               sorted(comparing(Task::getCreatedOn)).
               map(Task::getTitle).
               limit(n).
               collect(toList());
   }


    //Example 4:统计状态为reading的task的数量
    public long countAllReadingTasks(List<Task> tasks) {
        return tasks.stream().
                filter(task -> task.getType() == TaskType.READING).
                count();
    }


   //  Example 5: 非重复的列出所有task中的全部标签
   // flatMap操作把通过调用task.getTags().stream得到的各个stream合成到一个stream。
   // 一旦我们把所有的tag放到一个stream中，我们就可以通过调用distinct方法来得到非重复的tag。
   private static List<String> allDistinctTags(List<Task> tasks) {
      return tasks.stream().flatMap(task -> task.getTags().stream()).distinct().collect(toList());
   }

   //Example 6: 检查是否所有reading的task都有book标签
    public boolean isAllReadingTasksWithTagBooks(List<Task> tasks) {
        return tasks.stream().
                filter(task -> task.getType() == TaskType.READING).
                allMatch(task -> task.getTags().contains("books"));
    }

    //要判断所有reading的task中是否存在一个task包含java8标签，可以通过anyMatch来实现
    public boolean isAnyReadingTasksWithTagJava8(List<Task> tasks) {
        return tasks.stream().
                filter(task -> task.getType() == TaskType.READING).
                anyMatch(task -> task.getTags().contains("java8"));
    }

    //Example 7: 创建一个所有title的总览
    public static String joinAllTaskTitles(List<Task> tasks) {
        return tasks.stream().
                map(Task::getTitle).
                reduce((first, second) -> first + " *** " + second).
                get();
    }

    //    Example 8: 基本类型stream的操作
    public static void easyOperation(){
        //要创建一个值区间，可以调用range方法。range方法创建一个值为0到9的stream,不包含10。
        IntStream.range(0, 10).forEach(System.out::println);
        //rangeClosed方法允许我们创建一个包含上限值的stream。
        IntStream.rangeClosed(1, 10).forEach(System.out::println);
        //通过在基本类型的stream上使用iterate方法来创建无限的stream：
        LongStream infiniteStream = LongStream.iterate(1, el -> el + 1);
        //要从一个无限的stream中过滤出所有偶数，
        //infiniteStream.filter(el -> el % 2 == 0).forEach(System.out::println);
        //使用limit操作来现在结果stream的个数
        infiniteStream.filter(el -> el % 2 == 0).limit(100).forEach(System.out::println);

        //Example 9: 为数组创建stream
        String[] tags = {"java", "git", "lambdas", "machine-learning"};
        Arrays.stream(tags).map(String::toUpperCase).forEach(System.out::println);
    }



}
