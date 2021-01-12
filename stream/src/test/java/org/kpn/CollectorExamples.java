package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

@DisplayName("collector creation")
public class CollectorExamples {

    @DisplayName("join collector")
    @Test
    void joinCollector(){
        Collector<Object, StringBuilder, String> collector = Collector.of(
                StringBuilder::new,                         // init accumulator
                (b, s) -> b.append(s).append(" , "),        // handler of each element
                (b1, b2) -> b1.append(b2).append(" , "),    // joining of two elements
                StringBuilder::toString                     // last accumulator processing
        );

        Stream<String> stream = Stream.of("a", "b", "c");
        String line = stream.collect(collector);

        System.out.println(line);
    }

    @DisplayName("custom toList")
    @Test
    void customToList(){
        Collector<Object, ArrayList<Object>, ArrayList<Object>> collector = Collector.of(
                ArrayList::new,
                List::add,
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                }
        );

        Stream<String> stream = Stream.of("a", "b", "c");
        ArrayList<Object> list = stream.collect(collector);

        System.out.println(list);
    }
}
