package com.future.experience.fsbk;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/reorder-data-in-log-files/
 */
public class ReorderDatainLogFiles937 {
    public String[] reorderLogFiles(String[] logs) {
        if(logs == null || logs.length < 2) return logs;

        Arrays.sort(logs, (a, b) -> {
            int indexA = a.indexOf(' ') + 1;
            int indexB = b.indexOf(' ') + 1;
            if(a.charAt(indexA) >= '0' && a.charAt(indexA) <= '9') {
                return (b.charAt(indexB) >= '0' && b.charAt(indexB) <= '9') ? 0 : 1;
            }
            if(b.charAt(indexB) >= '0' && b.charAt(indexB) <= '9') return -1;
            int res = a.substring(indexA).compareTo(b.substring(indexB));
            return res == 0 ? a.substring(0, indexA - 1).compareTo(b.substring(0, indexB - 1)) : res;
        });

        return logs;
    }
}
