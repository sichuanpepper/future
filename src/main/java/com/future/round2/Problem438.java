package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 1/31/18.
 */
public class Problem438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] counter = new int[26];
        for(char ch : p.toCharArray()) counter[ch - 'a']++;
        int p1 = 0, p2 = 0;
        while(p2 < s.length()) {
            if(counter[s.charAt(p2) - 'a'] > 0) {
                counter[s.charAt(p2++) - 'a']--;
            } else {
                if(p1 < p2) {
                    counter[s.charAt(p1++) - 'a']++;
                } else {
                    p1++;
                    p2 = p1;
                }
            }
            if(p2 - p1 == p.length()) {
                res.add(p1);
                counter[s.charAt(p1++) - 'a']++;
            }
        }
        return res;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] counter = new int[26];
        for(char ch : p.toCharArray()) counter[ch - 'a']++;
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            if(counter[s.charAt(right++)]-- > 0) count--;
            if(count == 0) res.add(left);
            if(right - left == p.length() && counter[s.charAt(left++)]++ >=0) count++;
        }
        return res;
    }

}
