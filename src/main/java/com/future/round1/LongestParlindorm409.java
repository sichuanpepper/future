package com.future.round1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by someone on 5/15/17.
 */
public class LongestParlindorm409 {
    public int longestPalindrome(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }

        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap();
        for(char ch : chars) {
            if(map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        int length = 0;
        boolean hasOdd = false;
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() % 2 == 0) {
                length += entry.getValue();
            } else {
                length += entry.getValue() - 1;
                hasOdd = true;
            }
        }
        return hasOdd ? length + 1 : length;
    }
}
