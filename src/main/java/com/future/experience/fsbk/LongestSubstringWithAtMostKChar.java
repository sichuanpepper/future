package com.future.experience.fsbk;

/**
 * https://www.lintcode.com/problem/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringWithAtMostKChar {
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer

    Slide windown, the key is how to slide it.
    - Counter(c1) for each char, another counter(c2) for number of distinct char.
    - Keep moving right pointer utils the c2 > k, then keep moving left util c2 <= k
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() < 1 || k < 1) {
            return 0;
        }

        int[] c1 = new int[256];

        int left = 0, right = 0, c2 = 0, max = 0;
        while(right < s.length()) {
            char ch = s.charAt(right);
            if(c1[ch]++ == 0) { //a new char
                c2++;
            }
            if(c2 <= k) {
                max = Math.max(max, right - left + 1);
            } else {
                while(c2 > k) {
                    if(--c1[s.charAt(left)] == 0) {
                        c2--;
                    }
                    left++;
                }
            }
            right++;
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithAtMostKChar p = new LongestSubstringWithAtMostKChar();
        System.out.println("nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg".length());
        System.out.println(p.lengthOfLongestSubstringKDistinct("jukhkjpvqruitobiahxhgdrpqttsebjsg", 11));

    }
}
