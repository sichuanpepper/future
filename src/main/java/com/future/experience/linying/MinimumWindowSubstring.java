package com.future.experience.linying;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] counter = new int[256];
        for(char ch : t.toCharArray()) {
            counter[ch]++;
        }

        int left = 0, right = 0, cnt = 0;
        String str = null;
        while(right < s.length()) {
            if(cnt == t.length()) {
                //try to minimize length, move left and keep the cnt == t.length
                while(counter[s.charAt(left)] < 0) {
                    counter[s.charAt(left++)]++;
                }

                if(str == null || str.length() > (right - left)) {
                    str = s.substring(left, right);
                }
                counter[s.charAt(left++)]++;
                cnt--;
            } else {
                if(counter[s.charAt(right)] > 0) {
                    cnt++;
                }
                counter[s.charAt(right++)]--;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring p = new MinimumWindowSubstring();
        //"ADOBECODEBANC"
        //"ABC"
        System.out.println(p.minWindow("ADBECEBANC", "ABC"));
    }
}
