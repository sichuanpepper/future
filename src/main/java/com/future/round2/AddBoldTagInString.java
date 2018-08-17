package com.future.round2;


/**
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the
 * substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

 Example 1:

 Input:
 s = "abcxyz123"
 dict = ["abc","123"]
 Output:
 "<b>abc</b>xyz<b>123</b>"


 Example 2:

 Input:
 s = "aaabbcc"
 dict = ["aaa","aab","bc"]
 Output:
 "<b>aaabbc</b>c"


 Note:

 The given dict won't contain duplicates, and its length won't exceed 100.
 All the strings in input have length in range [1, 1000].

 * Created by xingfeiy on 8/13/18.
 */
public class AddBoldTagInString {
    /**
     * Analyze:
     * For each character ch[i], to find the longest word (max_word_length) which start with ch[i].
     *  - that means the substring (i, i + max_word_length) need to be wrap.
     * Since we have to merge the overlap, the characters between i and i + max_word_length may extend the substring.
     * @param s
     * @param dict
     * @return
     */
    public String addBoldTag(String s, String[] dict) {
        boolean[] status = new boolean[s.length()];
        int matchEnd = 0;
        for(int i = 0; i < s.length(); i++) {
            String prefix = Character.toString(s.charAt(i));
            for(String word : dict) {
                if(!word.startsWith(prefix)) continue;
                matchEnd = Math.max(matchEnd, i + word.length());
            }
            status[i] = i < matchEnd;
        }

        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (start < s.length()) {
            if(!status[start]) {
                sb.append(s.charAt(start++));
            } else {
                int end = start;
                while (end < s.length() && status[end]) end++;
                sb.append("<b>").append(s.substring(start, end)).append("</b>");
                start = end;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AddBoldTagInString a = new AddBoldTagInString();
        String[] dict = new String[]{"abc","123"};
        System.out.println(a.addBoldTag("abcxyz123", dict));
    }
}
