package com.future.foundation.algo;

import com.future.utils.DisplayUtils;

/**
 * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 * A good article to explain how KMP works.
 * <p>
 * Created by xingfeiy on 4/6/18.
 */
public class KMPStringSearch {
    /**
     * ex, pattern is ABCDABD, we can get the lps as [0,0,0,0,1,2,0]
     * substring "A"
     *      prefixes: nul
     *      suffixes: nul
     *      so the lps of substring is 0, lps[0] = 0
     * substring "AB"
     *      prefixes: A
     *      suffixes: B
     *      no common tokens, so the lps is 0, lps[1] = 0
     * substring "ABCDAB"
     *      prefixes: A, AB, ABC, ABCD, ABCDA
     *      suffixes: BCDAB, CDAB, DAB, AB, B
     *      has one common token "AB", the length is 2, lps[5] = 2
     * @param pattern
     */
    public static int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];

        int currMaxLPS = 0; //initial as first position.
        lps[0] = 0;

        for (int i = 1; i < pattern.length(); ) {
            //since all prefixes start with first character, so that's why we initial currMaxLPS as 0.
            // and only there's a character matches the first character, then we can compute currMaxLPS.
            if (pattern.charAt(i) == pattern.charAt(currMaxLPS)) {
                lps[i++] = ++currMaxLPS;
            } else {
                //Here is tricky, consider the case "AAACAAAA" and i = 7
                // pattern[7] != pattern[3] and currMaxLPS = 3
                // that means pattern.substring(0, 3) == pattern.substring(pattern.length() - 3, pattern.length())
                // Here is "AAA" == "AAA", and we also know the lps of third character 'A' is 2,
                //that means the first second character in left must equals the last two characters in right.
                if (currMaxLPS != 0) {
                    currMaxLPS = lps[currMaxLPS - 1];
                } else {
                    lps[i++] = 0;
                }
            }
        }
        return lps;
    }


    /**
     * KMP is just a re-application of the LPS concept shown above.
     * Its algorithm is very similar to the above one.
     */
    public static void kmp(String pattern, String txt) {
        int lps[] = computeLPS(pattern);
        DisplayUtils.printArray(lps);

        int txtPos = 0, patternPos = 0;
        while (txtPos < txt.length()) {
            if (pattern.charAt(patternPos) == txt.charAt(txtPos)) {
                patternPos++;
                txtPos++;
            }

            if (patternPos == pattern.length()) {
                System.out.println
                        ("Pattern is found at index " + (txtPos - patternPos));
                patternPos = lps[patternPos - 1];
            } else if (pattern.charAt(patternPos) != txt.charAt(txtPos)) {
                // mismatch occurs after j matches
                if (patternPos != 0)
                    patternPos = lps[patternPos - 1];
                else
                    txtPos++;
            }
        }
    }

    public static void main(String[] args) {
//        DisplayUtils.printArray(computeLPS("ABCDABD"));
        DisplayUtils.printArray(computeLPS("AAACAAAA"));
        kmp("ABCABC", "ABABCABCBA");
    }
}

