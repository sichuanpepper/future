package com.future.experience.airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all the combinations of a string in lowercase and uppercase. For example, string "ab" >>> "ab",
 * "Ab", "aB", "AB". So, you will have 2^n (n = number of chars in the string) output strings. The goal is for
 * you to test each of these strings and see if it matchs a hidden string.
 * <p>
 * Created by xingfeiy on 6/13/18.
 */
public class CharacterCombination {
    private boolean isBitSet(int n, int offset) {
        return (n >> offset & 1) != 0;
    }

    public List<String> ermutateString(String text) {
        List<String> res = new ArrayList<>();
        if (text == null || text.length() == 0) {
            return res;
        }
        char[] chars = text.toCharArray();
        for (int i = 0, n = (int) Math.pow(2, chars.length); i < n; i++) {
            char[] curr = new char[chars.length];
            for (int j = 0; j < chars.length; j++) {
                curr[j] = (isBitSet(i, j)) ? Character.toUpperCase(chars[j]) :
                        chars[j];
            }
            res.add(new String(curr));
        }
        return res;
    }
}
