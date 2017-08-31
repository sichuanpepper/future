package com.future.foundation.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xingfeiy on 3/28/17.
 */
public class Lambda {
    public static List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

    public static List<String> traditionalSort() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        return names;
    }

    public static List<String> lambdaV1 () {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (a, b) -> b.compareTo(a));
        return names;
    }


    //define a functional interface.
    @FunctionalInterface
    interface Convert<F, T> {
        T convert(F f);
    }

    public static Convert<String, Integer> convert = (from) -> Integer.valueOf(from);

    public static Convert<String, Integer> convertPlus = Integer::valueOf;

    public static void intToStr() {
        convert.convert("123");
        convertPlus.convert("123");

        int num = 1;
        Convert<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
//        num = 3;
    }



}
