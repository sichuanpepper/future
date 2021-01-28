package com.future.experience.gugou;

/**
 * https://leetcode.com/problems/compare-version-numbers/
 *
 * The solution is straightforward, just split string by '.' and compare it one by one.
 * Takeaways:
 * - String.split(), the parameter is regex, so we should escape the character '.'
 * - Integer.parseInt() will handle the leading zero case.
 */
public class CompareVersion {
    //digits, '.', leading zero
    public int compareVersion(String version1, String version2) {
        if(version1.equals(version2)) {
            return 0;
        }
        String[] revision1 = version1.split("\\.");
        String[] revision2 = version2.split("\\.");
        int p1 = 0, p2 = 0;
        while(p1 < revision1.length || p2 < revision2.length) {
            String r1 = (p1 < revision1.length) ? revision1[p1++] : null;
            String r2 = (p2 < revision2.length) ? revision2[p2++] : null;
            int num1 = strToInt(r1);
            int num2 = strToInt(r2);
            if(num1 < num2) {
                return -1;
            } else if(num1 > num2) {
                return 1;
            }
        }
        return 0;
    }

    private int strToInt(String str) {
        if(str == null || str.length() < 1) {
            return 0;
        }
        //remove leading zero
        int p = 0;
        while(p < str.length() && str.charAt(p) == '0') {
            p++;
        }
        if(p == str.length()) {
            return 0;
        }
        return Integer.parseInt(str.substring(p, str.length()));
    }

    public static void main(String[] args) {
        CompareVersion p = new CompareVersion();
        System.out.println(p.compareVersion("0.1", "1.1"));
        System.out.println(Integer.parseInt("0001"));
        System.out.println(Integer.parseInt("000"));
    }
}
