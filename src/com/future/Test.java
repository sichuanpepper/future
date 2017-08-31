package com.future;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by xingfeiy on 5/16/17.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Math.round((float) 5 / 2));
        char ch = (char)1;
        System.out.println(ch);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(1);
        System.out.println(set.size());
        Set<Character> firstLine = new HashSet(){};
    }

    public char findTheDifference(String s, String t) {
        int[] charCount = new int[128];
        for(int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i)]++;
        }

        for(int i = 0; i < t.length(); i++) {
            charCount[t.charAt(i)]--;
            if(charCount[t.charAt(i)] < 0) {
                return t.charAt(i);
            }
        }
        return Character.MAX_VALUE;
    }

    public static boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMap = new HashMap();
        Map<Character, Integer> tMap = new HashMap();
        for(int i = 0; i < s.length(); i++) {
            if(sMap.put(s.charAt(i), i) != tMap.put(t.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }


    public int solution1(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }

        int max = 0;
        int p1 = 0;
        int p2 = 0;
        Set<Character> set = new HashSet<>();
        while(p1 < s.length() && p2 < s.length()) {
            if(!set.contains(s.charAt(p2))) {
                set.add(s.charAt(p2++));
                max = Math.max(max, p2 - p1);
            } else {
                set.remove(s.charAt(p1++));
            }
        }
        return max;
    }

    public int solution2(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for(int p1 = 0, p2 = 0; p2 < s.length(); p2++) {
            if(map.containsKey(s.charAt(p2))) {
                //pay attention and think about why we have to use Math.max() here.
                p1 = Math.max(map.get(s.charAt(p2)) + 1, p1);
            }

            map.put(s.charAt(p2), p2);
            max = Math.max(max, p2 - p1 + 1);
        }
        return max;
    }

    public int solution3(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }

        int[] array = new int[128];
        int max = 0;
        for(int p1 = 0, p2 = 0; p2 < s.length(); p2++) {
            p1 = Math.max(array[s.charAt(p2)], p1);
            array[s.charAt(p2)] = p2 + 1;
            max = Math.max(max, p2 - p1 + 1);
        }
        return max;
    }
}
