package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@DisplayName("Number stream")
public class NumberStream {

    @DisplayName("sum")
    @Test
    void sum(){
        int sum = Stream.of("1", "2", "3", "4", "5", "6", "7").mapToInt(Integer::parseInt).sum();
        System.out.println(sum);
    }

    @DisplayName("average")
    @Test
    void average(){
        OptionalDouble average = Stream.of("1", "2", "3", "4", "5", "6", "7").mapToDouble(Double::parseDouble).average();
        System.out.println(average);
    }

    @DisplayName("mapToObj")
    @Test
    void mapToObj(){
        Object[] objects = IntStream.of(1, 2, 3, 4, 5, 6).mapToObj((IntFunction<Object>) Integer::new).toArray();
        System.out.println(Arrays.asList(objects));
    }
}
