package com.future.experience.fsbk.eley;

/**
 * https://www.lintcode.com/problem/valid-word-abbreviation/
 */
public class ValidWordAbbreviation {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     *
     * Thoughts:
     * The solution is pretty straightforward, user two pointer, but pay attention to the corner case, number with leading zero.
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        if(word.equals(abbr)) {
            return true;
        }

        int p1 = 0, p2 = 0;
        while(p1 < word.length() && p2 < abbr.length()) {
            if(Character.isDigit(abbr.charAt(p2))) {
                if(abbr.charAt(p2) == '0') {
                    return false;
                }
                int p = p2;
                while(p < abbr.length() && Character.isDigit(abbr.charAt(p))) {
                    p++;
                }
                int len = Integer.parseInt(abbr.substring(p2, p));
                p1 += len;
                p2 = p;
            } else {
                if(word.charAt(p1) != abbr.charAt(p2)) {
                    return false;
                }
                p1++;
                p2++;
            }
        }
        return p1 == word.length() && p2 == abbr.length();
    }
}
