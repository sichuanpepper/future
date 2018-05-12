package com.future.round2;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.

 For example, Given s = “eceba” and k = 2,

 T is "ece" which its length is 3.

 * Created by xingfeiy on 5/10/18.
 */
public class Problem159 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() < 1 || k < 1) return 0;
        if(k >= s.length()) return s.length();
        int[] counter = new int[256];
        int p1 = 0, p2 = 0, count = 0, max = 0;
        while (p1 < s.length() && p2 < s.length()) {
            if(counter[s.charAt(p2)]++ == 0) count++;
            if(count <= k) {
                max = Math.max(max, p2++ - p1 + 1);
            } else {
                if(--counter[s.charAt(p1++)] == 0) count--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Problem159 p = new Problem159();
        System.out.println(p.lengthOfLongestSubstringKDistinct("", 1)); //0
        System.out.println(p.lengthOfLongestSubstringKDistinct("", 0)); //0
        System.out.println(p.lengthOfLongestSubstringKDistinct("abc", 0));  //0
        System.out.println(p.lengthOfLongestSubstringKDistinct("abbbcde", 1));  //3
        System.out.println(p.lengthOfLongestSubstringKDistinct("abbbccde", 2));  //5
        System.out.println(p.lengthOfLongestSubstringKDistinct("abbbccde", 3));  //6
        System.out.println(p.lengthOfLongestSubstringKDistinct("aaaaa", 1));  //5

    }
}
