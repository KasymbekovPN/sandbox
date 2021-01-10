package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("Intermediate methods")
public class Intermediate {

    @DisplayName("filter")
    @Test
    void filter(){
        Stream<String> stream = Stream.of("a", "b", "c", "d", "e", "a", "b");
        List<String> list = stream.filter((ch) -> !ch.equals("a")).collect(Collectors.toList());

        System.out.println(list);
    }

    @DisplayName("skip")
    @Test
    void skip(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        List<Integer> list = stream.skip(3).collect(Collectors.toList());

        System.out.println(list);
    }

    @DisplayName("distinct - return without duplicate")
    @Test
    void distinct(){
        Stream<String> stream = Stream.of("1", "1", "2", "3", "3", "3");
        List<String> list = stream.distinct().collect(Collectors.toList());

        System.out.println(list);
    }

    @DisplayName("map")
    @Test
    void map(){
        Stream<Integer> stream = Stream.of(1, 2, 3);
        List<Integer> list = stream.map((i) -> i * i).collect(Collectors.toList());

        System.out.println(list);
    }

    @DisplayName("peek")
    @Test
    void peek(){
        Stream<String> stream = Stream.of("a", "b", "c");
        List<String> list = stream.peek((s) -> {
            System.out.print(s + ",");
        }).collect(Collectors.toList());

        System.out.println("\n" + list);
    }

    @DisplayName("limit")
    @Test
    void limit(){
        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).limit(3).collect(Collectors.toList());
        System.out.println(list);
    }

    @DisplayName("sorted")
    @Test
    void sorted(){
        List<String> list = Stream.of("a", "x", "z", "y", "c", "b").sorted().collect(Collectors.toList());
        System.out.println(list);
    }

    @DisplayName("sorted with comparator")
    @Test
    void sortedWithComparator(){
        Comparator<String> comparator = (s1, s2) -> -1 * s1.compareTo(s2);
        List<String> list = Stream.of("a", "x", "z", "y", "c", "b").sorted(comparator).collect(Collectors.toList());
        System.out.println(list);
    }

    @DisplayName("mapToInt/Double/Long")
    @Test
    void mapToNumber(){
        int[] ints = Stream.of("1", "2", "3").mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(ints));
    }

    @DisplayName("flatMap (Int/Double/Long)")
    @Test
    void flatMap(){
        String[] strings = Stream.of("a,b", "c,d").flatMap((s) -> Arrays.stream(s.split(","))).toArray(String[]::new);
        System.out.println(Arrays.asList(strings));
    }
}
