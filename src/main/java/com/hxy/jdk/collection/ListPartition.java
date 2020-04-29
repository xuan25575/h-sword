package com.hxy.jdk.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.collections.ListUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @Author huang_2
 * @Date 2020/4/24 7:34 下午
 * @Description TODO
 */

public class ListPartition {


    //2.使用Guava对List进行分区
    @Test
    public void givenList_whenParitioningIntoNSublists_thenCorrect() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> subSets = Lists.partition(intList, 3);

        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(subSets.size(), equalTo(3));
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }


    //3.使用Guava对Collection进行分区
    //请记住，分区是原始集合的子列表视图 - 这意味着原始集合中的更改将反映在分区中
    @Test
    public void givenCollection_whenParitioningIntoNSublists_thenCorrect() {
        Collection<Integer> intCollection = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        Iterable<List<Integer>> subSets = Iterables.partition(intCollection, 3);

        List<Integer> firstPartition = subSets.iterator().next();
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(1, 2, 3);
        assertThat(firstPartition, equalTo(expectedLastPartition));
    }

    @Test
    public void givenListPartitioned_whenOriginalListIsModified_thenPartitionsChangeAsWell() {
        // Given
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> subSets = Lists.partition(intList, 3);

        // When
        intList.add(9);

        // Then
        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8, 9);
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }

    //4.使用Apache Commons Collections对List进行分区
//    @Test
//    public void givenList_whenParitioningIntoNSublists_thenCorrect() {
//        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
//        List<List<Integer>> subSets = ListUtils.partition(intList, 3);
//
//        List<Integer> lastPartition = subSets.get(2);
//        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
//        assertThat(subSets.size(), equalTo(3));
//        assertThat(lastPartition, equalTo(expectedLastPartition));
//    }


    //5.使用Java8对List进行分区
    @Test
    public void givenList_whenParitioningIntoSublistsUsingPartitionBy_thenCorrect() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        Map<Boolean, List<Integer>> groups =
                intList.stream().collect(Collectors.partitioningBy(s -> s > 6));
        List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());

        List<Integer> lastPartition = subSets.get(1);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(subSets.size(), equalTo(2));
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }

    //5.2.  Collectors groupingBy
    //注意：就像Collectors.partitioningBy（）一样 - 生成的分区不会受到主List中的更改的影响。
    @Test
    public final void givenList_whenParitioningIntoNSublistsUsingGroupingBy_thenCorrect() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        Map<Integer, List<Integer>> groups =
                intList.stream().collect(Collectors.groupingBy(s -> (s - 1) / 3));
        List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());

        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(subSets.size(), equalTo(3));
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }

    //    5.3. 按分隔符拆分列表
    //注意：我们使用“0”作为分隔符 - 我们首先获得List中所有“0”元素的索引，然后我们在这些索引上拆分List。
    @Test
    public void givenList_whenSplittingBySeparator_thenCorrect() {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 0, 4, 5, 6, 0, 7, 8);

        int[] indexes =
                Stream.of(IntStream.of(-1), IntStream.range(0, intList.size())
                        .filter(i -> intList.get(i) == 0), IntStream.of(intList.size()))
                        .flatMapToInt(s -> s).toArray();
        List<List<Integer>> subSets =
                IntStream.range(0, indexes.length - 1)
                        .mapToObj(i -> intList.subList(indexes[i] + 1, indexes[i + 1]))
                        .collect(Collectors.toList());

        List<Integer> lastPartition = subSets.get(2);
        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
        assertThat(subSets.size(), equalTo(3));
        assertThat(lastPartition, equalTo(expectedLastPartition));
    }

}
