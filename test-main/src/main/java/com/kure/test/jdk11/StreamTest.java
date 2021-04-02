package com.kure.test.jdk11;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lenovo
 */
public class StreamTest {

    public static void main(String[] args) {
        System.out.println(Stream.ofNullable(null).count());

        Stream.of(1, 2, 3, 4, 5)
                .takeWhile(n -> n < 10)
                .collect(Collectors.toList()).forEach(System.out::println);

        Stream.of(1, 2, 3, 4, 5)
                .dropWhile(n -> n < 3)
                .collect(Collectors.toList()).forEach(System.out::println);

        Optional.of("javastack").orElseThrow(); // javastack
        Optional.of("javastack").stream().count(); // 1
        Optional.ofNullable(null)
                .or(() -> Optional.of("javastack"))
                .get();
    }
}
