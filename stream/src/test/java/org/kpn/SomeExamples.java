package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("SomeExamples")
public class SomeExamples {

    private final List<Integer> numbers = Arrays.asList(1,2,3,4);
    private final List<String> strings = Arrays.asList("a1","b2","c3", "a1");

    @DisplayName("Count amount of a1")
    @Test
    void countAmountOfA1(){
        long count = Stream.of("a1", "a2", "a3", "a1")
                .filter("a1"::equals).count();
        System.out.println(count);
    }

    @DisplayName("sum of odd numbers")
    @Test
    void sumOddNum(){
        final long sum = numbers.stream().collect(Collectors.summarizingInt((i) -> i % 2 == 1 ? i : 0)).getSum();
        System.out.println(sum);
    }

    @DisplayName("+1 to each and average")
    @Test
    void average(){
        double average = numbers.stream().collect(Collectors.summarizingInt((i) -> i - 1)).getAverage();
        System.out.println(average);
    }

    @DisplayName("get statistic")
    @Test
    void getStatistic(){
        IntSummaryStatistics collect = numbers.stream().collect(Collectors.summarizingInt((p) -> p + 3));
        System.out.println(collect);
    }

    @DisplayName("part")
    @Test
    void part(){
        Map<Boolean, List<Integer>> collect = numbers.stream().collect(Collectors.partitioningBy((p) -> p % 2 == 0));
        System.out.println(collect);
    }

    @DisplayName("without duplicate")
    @Test
    void withoutDuplicate(){
        List<String> collect = strings.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @DisplayName("joining")
    @Test
    void joining(){
        final String collect = strings.stream().collect(Collectors.joining(":", "<b>", "</b>"));
        System.out.println(collect);
    }

    @DisplayName("to map")
    @Test
    void toMap(){
        Map<String, String> collect = strings.stream().distinct().collect(Collectors.toMap((s) -> s.substring(0, 1), (s) -> s.substring(1, 2)));
        System.out.println(collect);
    }

    @DisplayName("grouping")
    @Test
    void grouping(){
        Map<String, List<String>> collect = strings.stream().collect(Collectors.groupingBy((s) -> s.substring(0, 1)));
        System.out.println(collect);
    }

    @DisplayName("gm")
    @Test
    void gm(){
        final Map<String, String> collect = strings
                .stream()
                .collect(Collectors.groupingBy((p) -> p.substring(0, 1), Collectors.mapping((p) -> p.substring(1, 2), Collectors.joining(":"))));
        System.out.println(collect);
    }
}
