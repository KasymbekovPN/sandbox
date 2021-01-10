package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("Terminal methods")
public class Terminal {

    @DisplayName("findFirst")
    @Test
    void findFirst(){
        Optional<String> first = Stream.of("a", "b", "c").findFirst();
        System.out.println(first);
    }

    @DisplayName("findAny")
    @Test
    void findAny(){
        Optional<String> any = Stream.of("a", "b", "c").findAny();
        System.out.println(any);
    }

    @DisplayName("collect")
    @Test
    void collect(){
        List<String> list = Stream.of("a", "b", "c").filter((s) -> !s.equals("b")).collect(Collectors.toList());
        System.out.println(list);
    }

    @DisplayName("count")
    @Test
    void count(){
        long count = Stream.of("a", "b", "c").count();
        System.out.println(count);
    }

    @DisplayName("anyMatch")
    @Test
    void anyMatch(){
        boolean b = Stream.of("a", "bb", "ccc").anyMatch(s -> s.length() == 1);
        System.out.println(b);
    }

    @DisplayName("noneMatch")
    @Test
    void noneMatch(){
        boolean b = Stream.of("a", "bb", "ccc").noneMatch(s -> s.length() == 5);
        System.out.println(b);
    }

    @DisplayName("allMatch")
    @Test
    void allMatch(){
        boolean b = Stream.of("a", "bb", "ccc").anyMatch(s -> s.length() >= 1);
        System.out.println(b);
    }

    @DisplayName("min")
    @Test
    void min(){
        Optional<String> min = Stream.of("a", "b", "c").min(String::compareTo);
        System.out.println(min);
    }

    @DisplayName("max")
    @Test
    void max(){
        Optional<String> max = Stream.of("a", "b", "c").max(String::compareTo);
        System.out.println(max);
    }

    @DisplayName("forEach - parallel execution order isn't guaranteed")
    @Test
    void forEach(){
        Stream<Integer> stream = Stream.iterate(1, n -> n + 1).limit(25).parallel();
        stream.forEach(System.out::println);
    }

    @DisplayName("forEachOrdered - parallel execution order is guaranteed")
    @Test
    void forEachOrder(){
        Stream<Integer> stream = Stream.iterate(1, n -> n + 1).limit(25).parallel();
        stream.forEachOrdered(System.out::println);
    }

    @DisplayName("toArray")
    @Test
    void toArray(){
        Object[] objects = Stream.of("a", "b", "c").toArray();
        System.out.println(Arrays.asList(objects));
    }

    @DisplayName("reduce")
    @Test
    void reduce(){
        Optional<String> reduce = Stream.of("a", "b", "c").reduce((s1, s2) -> s1 + s2);
        System.out.println(reduce);
    }
}
