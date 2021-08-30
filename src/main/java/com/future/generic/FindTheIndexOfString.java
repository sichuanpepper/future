package com.future.generic;

public class FindTheIndexOfString {
    /**
     * Find the index of pattern in the given string, the pattern may contains wildcard '*' which represents zero or multiple any characters.
     * ex:
     * "abcdefg", "cd", returns 2
     * "abcdefg", "qe", return -1
     * "abcdefg", "b*d", return 1
     *
     * Thoughts:
     * There are two scenarios:
     * 1. pattern doesn't contain wildcard
     * 2. pattern contain wildcard
     *  2.1. pattern starts with *, just ignore leading * and find the rest part.
     *  2.2. pattern has * in the middle, find leading letters util *, then move to 2.1
     *  2.3. pattern ends with *, ignore it.
     *
     * @param str
     * @param pattern
     * @return
     */
    public static int findIndex(String str, String pattern) {
        return helper(str, 0, pattern);
    }

    private static int helper(String str, int start, String pattern) {
        if(pattern.startsWith("*")) {
            return helper(str, start, pattern.substring(1));
        }
        if(start >= str.length()) {
            return -1;
        }

        if(pattern == null || pattern.length() < 1) {
            return 0;
        }

        int p = 0, s = start;
        while (start < str.length() && p < pattern.length()) {
            if(pattern.charAt(p) == '*') {
                int res = helper(str, start, pattern.substring(p + 1));
                return res >= 0 ? s : res;
            } else if(pattern.charAt(p) != str.charAt(start)) {
                return helper(str, start + 1, pattern);
            } else {
                start++;
                p++;
            }
        }
        return start < str.length() ? s : helper(str, start, pattern.substring(p));
    }

    public static void main(String[] args) {
        System.out.println(findIndex("abcdef", "cde"));
        System.out.println(findIndex("abcdef", "cdc"));
        System.out.println(findIndex("abcdef", "c*e"));
        System.out.println(findIndex("abcdef", "*c*e"));
        System.out.println(findIndex("abcdef", "*c*e*"));
    }
}
