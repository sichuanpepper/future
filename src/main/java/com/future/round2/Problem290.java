package com.future.round2;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/word-pattern/description/
 *
 * Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

 Example 1:

 Input: pattern = "abba", str = "dog cat cat dog"
 Output: true
 Example 2:

 Input:pattern = "abba", str = "dog cat cat fish"
 Output: false
 Example 3:

 Input: pattern = "aaaa", str = "dog cat cat dog"
 Output: false
 Example 4:

 Input: pattern = "abba", str = "dog dog dog dog"
 Output: false
 Notes:
 You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

 * Created by xingfeiy on 8/19/18.
 */
public class Problem290 {
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null) return false;
        String[] words = str.split(" ");
        if(words.length != pattern.length()) return false;
        Map<String, Integer> wordMap = new HashMap<>();
        Map<Character, Integer> patMap = new HashMap<>();

        //why Integer??? we got failed if we use int here
        for(Integer i = 0; i < pattern.length(); i++) {
            if(patMap.put(pattern.charAt(i), i) != wordMap.put(words[i], i)) return false;
        }
        return true;
    }

    public static void main(String[] arags) {
        Problem290 p = new Problem290();
        System.out.println(p.wordPattern("ccccccccccc", "s s s s s s s s s s s"));

        System.out.println((1 != 0));
    }

}
