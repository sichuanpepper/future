package com.future.java.lambda.functionalinterface;


public class FuntioalInterfaceTest {
    public static void main(String[] args) {
        FunctionalInterface fi = (String str) -> {
            System.out.println("this is functional: " + str);
        };
        fi.print("Hello");
        fi.printWithPrefix("hello", "pre_");
    }
}
