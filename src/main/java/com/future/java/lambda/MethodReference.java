package com.future.java.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * The double colon (::) operator is called method reference.
 *
 * https://mkyong.com/java8/java-8-method-references-double-colon-operator/
 */
public class MethodReference {
    public static void print() {
        List<String> list = Arrays.asList("1", "2", "3");
        //anonymous class, forEach take interface Consumer as paras.
        list.forEach(new Consumer<String>() {
            private int counter = 0;  //state.
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // lambda expression, forEach method take interface Consumer as parameter,
        // Consumer is an interface with single method(also known as functional interface).
        // So the lambda expression can match with Consumer interface.

        //Functional interface can be implemented by lambda expression
        Consumer<String> func = (String str) -> {
            System.out.println(str);
        };

        //the difference between annoymous class and lambda expression.
        // annoymous class can have state(the counter above)
        // but lambda is stateless.
        list.forEach((String str) -> System.out.println(str));
        //in lambda, the type can be inferred.
        list.forEach(str -> System.out.println(str));


        //method reference
        list.forEach(System.out::println);

    }

    public static int addByTen(int a, int b) {
        a *= 10;
        b *= 10;
        return a + b + 10;
    }

    public static void main(String[] args) {
        //create lambda instance
        IntegerCalculator myCal = (a, b) -> MethodReference.addByTen(a, b);
        System.out.println(myCal.add(1, 2));
        //since all lambda body does is forward the parameters to the MethodReference.addByTen, we can use lambda method reference
        IntegerCalculator nextCal = MethodReference::addByTen;
        System.out.println(nextCal.add(1, 2));
    }
}
