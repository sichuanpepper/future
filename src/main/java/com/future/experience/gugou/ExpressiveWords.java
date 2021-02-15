package com.future.experience.gugou;

/**
 * https://leetcode.com/problems/expressive-words/
 */
public class ExpressiveWords {
    /**
     * S can be extend by word, then the length of word must less than or equal to the length of S.
     * We can use two pointers here, p1 points S and p2 points word, and move it from left to right, for each movement:
     *      - if S.charAt(p1) != word.charAt(p2) return false
     *      - if equals, then find the group in the S and word, let's use group1 and group2 respectively.
     *              - if length of group1 == group2, move p1 and p2 to the end of group and continue moving.
     *              - if length of group1 < group2, return false;
     *              - return length of group1 >= 3 or not
     *
     * @param S
     * @param words
     * @return
     */
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for(String word : words) {
            res += canExtend(S, word);
        }
        return res;
    }

    private int canExtend(String S, String word) {
        if(word.length() > S.length()) {
            return 0;
        }

        int p1 = 0, p2 = 0;
        while(p2 < word.length() && p1 < S.length()) {
            if(S.charAt(p1) != word.charAt(p2)) {
                return 0;
            }
            int p1Tmp = p1, p2Tmp = p2;
            while(p1Tmp < S.length() && S.charAt(p1Tmp) == S.charAt(p1)) p1Tmp++;
            while(p2Tmp < word.length() && word.charAt(p2Tmp) == word.charAt(p2)) p2Tmp++;
            if((p2Tmp - p2) > (p1Tmp - p1)) {
                return 0;
            }
            if ((p2Tmp - p2) != (p1Tmp - p1) && (p1Tmp - p1) < 3) {
                return 0;
            }

            p1 = p1Tmp;
            p2 = p2Tmp;
        }
        return (p1 == S.length()) && (p2 == word.length()) ? 1 : 0;
    }
}
