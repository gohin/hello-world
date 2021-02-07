package com.kure.test.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aaa","bbb","ccc").stream().collect(Collectors.toList());
//        Collections.fill(list, "dddd");
//        list.stream().forEach(System.out::println);

        Collections.reverse(list);
        list.stream().forEach(System.out::println);

        System.out.println(Collections.binarySearch(list, "a"));
        list.stream().forEach(System.out::println);
    }
}
