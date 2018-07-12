package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 7/11/18.
 */
public class ErmutateString {
    /**
     * Find all the combinations of a string in lowercase and uppercase. For example, string "ab" >>> "ab",
     * "Ab", "aB", "AB". So, you will have 2^n (n = number of chars in the string) output strings.
     * @param text
     * @return
     */
    public List<String> ermutateString(String text) {
        List<String> res = new ArrayList<>();
        if(text == null || text.length() < 1) return res;
        helper(text, 0, "", res);
        return res;
    }

    private void helper(String text, int start, String curStr, List<String> res) {
        if(start == text.length()) {
            res.add(curStr);
            return;
        }
        helper(text, start + 1, curStr + text.charAt(start), res);
        helper(text, start + 1, curStr + Character.toUpperCase(text.charAt(start)), res);
    }


    public static void main(String[] args) {
        ErmutateString e = new ErmutateString();
        DisplayUtils.printList(e.ermutateString("a"));
        System.out.println("---------");
        DisplayUtils.printList(e.ermutateString("ab"));
        System.out.println("---------");
        DisplayUtils.printList(e.ermutateString("abc"));
    }
}
