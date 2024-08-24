package org.example.spring.practice.generics;

public class Generics {
    public static <T> T getMiddle(T... args) {
        // T... is the varargs which enables to pass multiple values. and it always should be at the end in the case if the method takes more arguments
        return args[args.length/2];
    }

    public static void main(String[] args) {
        System.out.println(getMiddle(1,2,3,4,5));   // test with Integer
        System.out.println(getMiddle("A","B","C")); // test with String
    }
}