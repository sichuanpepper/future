package com.future.foundation.stringarray;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xingfeiy on 5/1/17.
 */
public class Analyse {

    //Why we usually discuss string && array algorithm together?
    //Because a lot of string problems can be transfer to array problems.
    //When you meet a string problem, first ask following questions,
    // 1. first thing, does this string consists of latin alphabet?
    // 2. all uppercase or lowercase or mix?
    //then, make sure you are familiar with these Java APIs.
    // string.length(), here length() is a method, but length is a parameter in array.

    // if you want to transfer a string into an array, you are not going to miss toCharArray() method.
    // this method returns an array(char[], not Character[]), sometimes, you have to transfer array into List or Set.
    public static void analyse() {
        String str = "abc";
        str.length();
        char[] chars = str.toCharArray();
//        Character[] characters = ArrayUtils.
//        List<Character> charList = Arrays.asList(new Character[]{});
        //array
        int[] array = new int[]{1, 2, 3};
        int a = array.length;
    }
}
