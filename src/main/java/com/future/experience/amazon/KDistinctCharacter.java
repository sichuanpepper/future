package com.future.experience.amazon;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 3/9/18.
 */
public class KDistinctCharacter {
    /**
     * Find all substring with k distinct characters and length k.
     * @param str
     * @param k
     * @return
     */
    public List<String> problem1(String str, int k) {
        List<String> res = new ArrayList<>();
        if(str == null || str.length() < 1 || str.length() < k) return res;
        int left = 0, right = 0;
        boolean[] characters = new boolean[256];
        while (right < str.length()) {
            if(characters[str.charAt(right)] || (right - left + 1) > k) {
                characters[str.charAt(left++)] = false;
            } else {
                if(right - left + 1 == k) res.add(str.substring(left, right + 1));
                characters[str.charAt(right++)] = true;
            }
        }
        return res;
    }

    /**
     * Find all substring with k - 1 distinct characters and length k
     * @param str
     * @param k
     * @return
     */
    public List<String> problem2(String str, int k) {
        List<String> res = new ArrayList<>();
        if(str == null || str.length() < 1 || str.length() < k) return res;

        return res;
    }

    public static void main(String[] args) {
        KDistinctCharacter problem = new KDistinctCharacter();
        DisplayUtils.printList(problem.problem1("", 0));
        DisplayUtils.printList(problem.problem1("abc", 4));
        DisplayUtils.printList(problem.problem1("abc", 2));
        DisplayUtils.printList(problem.problem1("aaaaaaaaaa", 1));
        DisplayUtils.printList(problem.problem1("aaaaaaaaaa", 2));
        DisplayUtils.printList(problem.problem1("abcdef", 3));
        DisplayUtils.printList(problem.problem1("abcccccdef", 3));
    }
}
