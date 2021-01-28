package com.future.experience.gugou;

import java.util.HashMap;
import java.util.Map;

/**
 * replace字符串
 * Example 1.
 * "X" --> 123
 * "Y" --> 456
 *
 * "%X%_%Y%" --> 123_456
 *
 * Example 2.
 * "usr" --> "google"
 * "HOME" --> "/usr/%usr%"
 *
 * "%HOME%" --> "/usr/google"
 * - Find the first pos of '%'
 *      - pos < 0, return given str.
 *      - pos >= 0, find the next '%'
 *          - next pos < 0, return given str.
 *          - next pos > 0, we got a variable.
 *              -  variable existed in map, get the value and pass it to replace func, and append the result into sb.
 *              - variable not existed, append substring(fpos + 1, spos), and pass the remains str to replace func.
 */
public class ReplaceString {
    public String replace(Map<String, String> wordMap, String str) {
        if(str == null || str.length() < 1) return "";
        int fIdx = str.indexOf('%');
        if(fIdx < 0) return str;
        int sIdx = str.indexOf('%', fIdx + 1);
        if(sIdx < 0) return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, fIdx));
        if(wordMap.containsKey(str.substring(fIdx + 1, sIdx))) {
            sb.append(replace(wordMap, wordMap.get(str.substring(fIdx + 1, sIdx))));
        } else {
            sb.append(str.substring(fIdx, sIdx + 1));
        }
        sb.append(replace(wordMap, str.substring(sIdx + 1, str.length())));
        return sb.toString();
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "123");
        map.put("usr", "google/%id");
        map.put("home", "/usr/%usr%/%id%/test");
        ReplaceString p = new ReplaceString();
        System.out.println(p.replace(map, "%home%"));
    }
}
