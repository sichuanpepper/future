package com.future.round2;

import java.util.ArrayList;
import java.util.List;

/**
 * [tag] Facebook
 * https://leetcode.com/problems/camelcase-matching/
 * A query word matches a given pattern if we can insert lowercase letters to the pattern word so that it equals the query. (We may insert each character at any position, and may insert 0 characters.)
 *
 * Given a list of queries, and a pattern, return an answer list of booleans, where answer[i] is true if and only if queries[i] matches the pattern.
 *
 *
 *
 * Example 1:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * Output: [true,false,true,true,false]
 * Explanation:
 * "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
 * "FootBall" can be generated like this "F" + "oot" + "B" + "all".
 * "FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".
 * Example 2:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * Output: [true,false,true,false,false]
 * Explanation:
 * "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
 * "FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".
 * Example 3:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * Output: [false,true,false,false,false]
 * Explanation:
 * "FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".
 *
 *
 * Note:
 *
 * 1 <= queries.length <= 100
 * 1 <= queries[i].length <= 100
 * 1 <= pattern.length <= 100
 * All strings consists only of lower and upper case English letters.
 */
public class Problem1023 {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>(queries.length);
        for(String query : queries) {
            res.add(match(query, pattern));
        }
        return res;
    }

    private boolean match(String query, String pattern) {
        if(query.equals(pattern)) return true;
        int p1 = 0, p2 = 0;
        for(;p1 < query.length() ;) {
            if(p2 < pattern.length() && query.charAt(p1) == pattern.charAt(p2)) {
                p1++;
                p2++;
            } else if(query.charAt(p1) >= 'A' && query.charAt(p1) <= 'Z') {
                return false;
            } else {
                p1++;
            }
        }
        return (p1 == query.length()) && (p2 == pattern.length());
    }


    public static void main(String[] args) {
        Problem1023 p = new Problem1023();
        System.out.println(p.camelMatch(new String[]{"FooBar"}, "FB"));
    }
}
