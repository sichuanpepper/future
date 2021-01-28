package com.future.java.lambda.functionalinterface;

/**
 * The functional interface is an interface with single abstract(unimplemented) method.
 * - Interface can contain both default methods and static methods, that means lambda expression can implement interface with more than one method.
 */
public interface FunctionalInterface {
    //the only one abstract method.
    void print(String str);

    default public void printWithSuffix(String str, String suffix) {
        System.out.println(str + suffix);
    }

    default public void printWithPrefix(String str, String prefix) {
        System.out.println(prefix + str);
    }

    static void printAsStatic(String str) {
        System.out.println("static_"  + str);
    }
}
