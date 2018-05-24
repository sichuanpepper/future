package com.future;

import java.util.*;

/**
 * Created by someone on 5/16/17.
 */
public class Test {
    public static void main(String[] args) {
        Random r =  new Random();
        for(int i = 0; i < 1000; i++) System.out.println(r.nextFloat());
        System.out.println(1/0.001);
        int index = 0;
        while (index < 26) {
            System.out.println((char)('a' + index++));
        }
        String word = "hello";
        System.out.println("=====");
        System.out.println(word.substring(0, 0));

        System.out.println(word.substring(word.length(), word.length()));
        System.out.println("=====");
        for(int i = 0; i < 10; i++) {
            System.out.print("Current: " + i + "\r\n");
            System.out.print("Current: " + i + " copy \r\n");
//            System.out.println("Current: " + i + " copy" + "\r");
//            System.out.println("\r\r\r");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(new ArrayList<>(), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        String str = "/.".substring(1, 2);
        String str2 = ".";
        System.out.println(str);
        System.out.println(str2);
        if(str.equals(str2)) {
            System.out.println();
        }
        if(str == ".") {
            System.out.println();
        }

        System.out.println(simplifyPath("/.."));
        System.out.println(-129 % 10);
        System.out.println(Math.abs(-2147483648));
        System.out.println(Math.abs(-2147483647));
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

    public static String simplifyPath(String path) {
        if(path == null || path.length() < 1) {
            return path;
        }

        Stack<String> stack = new Stack<>();
        for(String str : path.split("/")) {
            if(str.equals("..")) {
                if(!stack.isEmpty()) {stack.pop();}

            } else if (!str.equals(".") && str.length() > 0) {
                stack.push(str);
            }
        }

        String res = "";
        while(!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res.length() < 1 ? "/" : res;
    }
}
