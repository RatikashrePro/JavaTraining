package com;

import java.util.*;
import java.util.stream.*;

public class intermediate {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 3, 2, 8, 10, 7, 1);

        // INTERMEDIATE OPERATORS
        Stream<Integer> stream = numbers.stream()
                .filter(n -> n > 3)        // keep > 3
                .map(n -> n * 2)           // double each
                .distinct()                // remove duplicates
                .sorted()                  // sort ascending
                .skip(1)                   // skip first
                .limit(4);                 // take 4

        // TERMINAL OPERATOR: collect
        List<Integer> result = stream.collect(Collectors.toList());
        System.out.println("Final list (after intermediate ops): " + result);

        // TERMINAL OPERATOR: forEach
        System.out.print("forEach: ");
        numbers.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();

        // TERMINAL OPERATOR: count
        long count = numbers.stream().filter(n -> n % 2 == 0).count();
        System.out.println("Count of even numbers: " + count);

        // TERMINAL OPERATOR: reduce
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum of all numbers: " + sum);

        // TERMINAL OPERATOR: min & max
        int min = numbers.stream().min(Integer::compare).get();
        int max = numbers.stream().max(Integer::compare).get();
        System.out.println("Min = " + min + ", Max = " + max);

        // TERMINAL OPERATOR: toArray
        Integer[] arr = numbers.stream().toArray(Integer[]::new);
        System.out.println("Array: " + Arrays.toString(arr));

        // SHORT-CIRCUITING TERMINAL OPS
        boolean anyMatch = numbers.stream().anyMatch(n -> n > 9);
        boolean allMatch = numbers.stream().allMatch(n -> n > 0);
        Optional<Integer> first = numbers.stream().filter(n -> n % 2 == 0).findFirst();

        System.out.println("Any number > 9? " + anyMatch);
        System.out.println("All numbers > 0? " + allMatch);
        System.out.println("First even number: " + first.orElse(null));
    }
}
