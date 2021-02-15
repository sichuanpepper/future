package com.future.experience.fsbk;

/**
 *
 A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWay91 {
    public int numDecodings(String s) {
        if(s == null || s.length() < 1) return 0;
        return helper(s, 0);
    }

    private int helper(String s, int start) {
        if(start >= s.length()) return 1;
        if(s.charAt(start) == '0') return 0;
        int sum = 0;
        sum += helper(s, start + 1);
        if(start < s.length() - 1 && Integer.parseInt(s.substring(start, start + 2)) < 27)
            sum += helper(s, start + 2);
        return sum;
    }

    public int numDecodingsDB(String s) {
        if(s == null || s.length() < 1 || s.charAt(0) == '0') return 0;
        int first = 1, second = 1;
        for(int i = 1; i < s.length(); i++) {
            int tmp = 0;
            if(s.charAt(i) == '0') {
                tmp += first;
            } else if(Integer.parseInt(s.substring(i - 1, i + 1)) < 27) {
                tmp += (first + second);
            } else {
                tmp += second;
            }
            first = second;
            second = tmp;
        }
        return second;
    }
}
