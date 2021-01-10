package org.kpn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ClassLoaderUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@DisplayName("creating a stream")
public class Creation {

    @DisplayName("creating a stream from a collection")
    @Test
    void creatingStreamFromCollection(){
        List<String> collection = Arrays.asList("a1", "b2", "c3");
        Stream<String> stream = collection.stream();

        stream.forEach(System.out::println);
    }

    @DisplayName("creating a stream from values")
    @Test
    void creatingStreamFromValues(){
        Stream<String> stream = Stream.of("a", "b", "c");

        stream.forEach(System.out::println);
    }

    @DisplayName("creating a stream from an array")
    @Test
    void creatingStreamFromArray(){
        String[] array = {"a", "b", "c"};
        Stream<String> stream = Arrays.stream(array);

        stream.forEach(System.out::println);
    }

    @DisplayName("creating a stream from a file - each line is element")
    @Test
    void creatingStreamFromFile() throws URISyntaxException, IOException {
        URL url = getClass().getClassLoader().getResource("file4stream.txt");
        assert url != null;
        Path path = Paths.get(url.toURI());
        Stream<String> stream = Files.lines(path);

        stream.forEach(System.out::println);
    }

    @DisplayName("creating a stream from a string")
    @Test
    void creatingStringFromString(){
        IntStream chars = "123".chars();

        chars.forEach(System.out::println);
    }

    @DisplayName("creating a stream using a builder")
    @Test
    void creatingStreamUsingBuilder(){
        Stream<Object> stream = Stream.builder().add("a").add("b").add("c").build();

        stream.forEach(System.out::println);
    }

    @DisplayName("creating a parallel stream")
    @Test
    void creatingParallelStream() {
        List<String> collection = Arrays.asList("a1", "b2", "c3");
        Stream<String> stream = collection.parallelStream();

        stream.forEach(System.out::println);
    }

    @DisplayName("creating a stream using iterate")
    @Test
    void creatingStreamUsingIterate(){
        Stream<Integer> stream = Stream.iterate(1, n -> n + 1).limit(10);

        stream.forEach(System.out::println);
    }

    @DisplayName("creating a stream using generate")
    @Test
    void creatingStreamUsingGenerate(){
        Random random = new Random();
        Stream<Integer> stream = Stream.generate(random::nextInt).limit(20);

        stream.forEach(System.out::println);
    }
}
