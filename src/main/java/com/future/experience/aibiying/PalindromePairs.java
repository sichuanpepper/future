package com.future.experience.aibiying;

import java.util.*;

/**
 * Created by xingfeiy on 6/14/18.
 */
public class PalindromePairs {
    /**
     * Analyze:
     * - bat && tab, w1 == reverse(w2)
     * - llsa && as, w1 consists of palindorme prefix or suffix, and w2 is reverse(remains)
     *   - palindrome prefix, llsa && as -> word2+word1
     *   - palindrome suffix, sall && as -> word1+ word2
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words == null || words.length < 2) return res;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++) map.put(words[i], i);
        for(int curPos = 0; curPos < words.length; curPos++) {
            String word = words[curPos];
            for(int i = 0; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                String suffix = word.substring(i, word.length());
                if(isPalindrome(prefix)) {
                    String reversSuffix = new StringBuilder(suffix).reverse().toString();
                    if(map.containsKey(reversSuffix) && map.get(reversSuffix) != curPos) {
                        res.add(Arrays.asList(new Integer[]{map.get(reversSuffix), curPos}));
                    }
                }
                if(isPalindrome(suffix)) {
                    String reversePrefix = new StringBuilder(prefix).reverse().toString();
                    if(map.containsKey(reversePrefix) && map.get(reversePrefix) != curPos && suffix.length() > 0) {
                        res.add(Arrays.asList(new Integer[]{curPos, map.get(reversePrefix)}));
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str) {
        if(str.length() < 2) return true;
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if(str.charAt(left++) != str.charAt(right--)) return false;
        }
        return true;
    }

}
