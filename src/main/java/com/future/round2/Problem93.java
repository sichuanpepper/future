package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/description/
 *
 Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 For example:
 Given "25525511135",

 return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

 * Created by xingfeiy on 12/29/17.
 */
public class Problem93 {
    /**
     * Analyze:
     * Back tracking
     * IP address consists of 4 digits between 0 to 255.
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() < 4) return res;
        helper(s, 0, "", 0, res);
        return res;
    }

    private void helper(String s, int start, String curRes, int curLength, List<String> res) {
        if(start >= s.length()) {
            if(curLength == 4) res.add(curRes);
            return;
        }
        if(curLength > 4) return;
        curRes = curRes.isEmpty() ? "" : curRes + ".";
        helper(s, start + 1, curRes + s.substring(start, start + 1), curLength + 1, res);
        if(start + 2 <= s.length() && s.charAt(start) != '0')
            helper(s, start + 2, curRes + s.substring(start, start + 2), curLength + 1, res);
        if(start + 3 <= s.length() && s.charAt(start) != '0' && Integer.parseInt(s.substring(start, start + 3)) < 256)
            helper(s, start + 3, curRes + s.substring(start, start + 3), curLength + 1, res);
    }

    public static void main(String[] args) {
        Problem93 p = new Problem93();
        DisplayUtils.printList(p.restoreIpAddresses("010010"));
    }
}
