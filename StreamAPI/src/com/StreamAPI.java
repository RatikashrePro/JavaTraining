package com;
import java.util.*;
import java.util.stream.*;
public class StreamAPI {
	public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        //nums.stream().filter(n -> n%2==0).forEach(System.out::println);
        List<Integer> arr=nums.stream().map(n -> n*n).collect(Collectors.toList());
        int n=nums.stream().reduce(0, Integer :: sum);
        System.out.println(n);
        System.out.println(arr);
        
        List<String> names = Arrays.asList("Alex", "Bob", "Alice", "Sam");
        //names.stream().filter(name -> name.startsWith("A")).forEach(System.out:: println);
        String k=names.stream().min(Comparator.comparingInt(String:: length)).get();
        System.out.println(k);
        
        String hk=names.stream().reduce("",(a,b) -> a +" "+b);
        System.out.println(hk);
        	
	}
}
