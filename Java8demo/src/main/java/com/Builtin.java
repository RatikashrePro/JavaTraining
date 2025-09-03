package com;

import java.util.function.*;

public class Builtin {
    public static void main(String[] args) {

        // 1. Supplier: No input, returns a value
        Supplier<String> supplier = () -> "Hello from Supplier!";
        System.out.println("Supplier: " + supplier.get());

        // 2. Consumer: Takes input, no return value
        Consumer<String> consumer = (name) -> System.out.println("Consumer: Welcome, " + name);
        consumer.accept("Ratikashre");

        // 3. Function: Takes input, returns output
        Function<Integer, String> function = (n) -> "Number squared = " + (n * n);
        System.out.println("Function: " + function.apply(5));

        // 4. Predicate: Takes input, returns boolean
        Predicate<Integer> predicate = (n) -> n > 10;
        System.out.println("Predicate (15 > 10?): " + predicate.test(15));
        System.out.println("Predicate (5 > 10?): " + predicate.test(5));

        // 5. BiFunction: Takes 2 inputs, returns a result
        BiFunction<String, Integer, String> biFunction = (name, age) -> name + " is " + age + " years old";
        System.out.println("BiFunction: " + biFunction.apply("Alice", 25));

        // Extra: chaining BiFunction with Function (andThen)
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        Function<Integer, String> toStringFunc = (sum) -> "Sum is: " + sum;

        String result = add.andThen(toStringFunc).apply(10, 20);
        System.out.println("BiFunction with andThen: " + result);
    }
}
