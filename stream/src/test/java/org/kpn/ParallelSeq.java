package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

@DisplayName("Parallel and sequential")
public class ParallelSeq {

    @Test
    void test(){
        Stream<Integer> stream = Stream.of(1, 2, 3);
        System.out.println("is parallel : " + stream.isParallel());

        Stream<Integer> parallel = stream.parallel();
        System.out.println("is sequential : " + parallel.isParallel());

        Stream<Integer> sequential = parallel.sequential();
        System.out.println("is parallel : " + sequential.isParallel());
    }
}
