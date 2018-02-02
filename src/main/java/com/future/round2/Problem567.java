package com.future.round2;

/**
 * Created by xingfeiy on 1/31/18.
 */
public class Problem567 {
    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s2 == null) return false;
        int[] counter = new int[26];
        for(char ch : s1.toCharArray()) counter[ch - 'a']++;

        int p1 = 0, p2 = 0;
        while(p2 < s2.length()) {
            if(counter[s2.charAt(p2) - 'a'] > 0) {
                counter[s2.charAt(p2++) - 'a']--;
            } else {
                if(p1 < p2) {
                    counter[s2.charAt(p1++) - 'a']++;
                } else {
                    p1++;
                    p2 = p1;
                }

            }
            if(p2 - p1 == s1.length()) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Problem567 p = new Problem567();
        System.out.println(p.checkInclusion("hello", "ooolleoooleh"));
    }
}
