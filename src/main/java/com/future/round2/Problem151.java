package com.future.round2;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/description/


 Given an input string, reverse the string word by word.

 For example,
 Given s = "the sky is blue",
 return "blue is sky the".

 Update (2015-02-12):
 For C programmers: Try to solve it in-place in O(1) space.

 * Created by xingfeiy on 1/2/18.
 */
public class Problem151 {
    /**
     * Analyze:
     * This problem is not an algorithm problem, the difficult is if you can come out all test cases.
     *
     * Test cases:
     * "" => ""
     * "a" => "a"
     * "ab" => "ab"
     * "   a b  " => "   b a  "
     * " ac b!" => "b ac!"
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if(s == null) return s;
        char[] chars = s.toCharArray();

        helper(chars, 0, chars.length);
        int wordStart = -1;
        for(int i = 0; i < chars.length; i++) {
            if(wordStart == -1 && Character.isLetter(chars[i])) {
                wordStart = i;
                continue;
            }
            //find a word
            if(wordStart != -1 && !Character.isLetter(chars[i])) {
                helper(chars, wordStart, i - 1);
                wordStart = -1;
            }
        }
        if(wordStart != -1) helper(chars, wordStart, chars.length - 1);
        //clean space
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < chars.length; i++) {
            if(i > 0 && chars[i - 1] == ' ' && chars[i] == ' ') continue;
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    private void helper(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start++] = chars[end];
            chars[end--] = tmp;
        }
    }

    public static void main(String[] args) {
        Problem151 p = new Problem151();
        System.out.println(p.reverseWords(" "));
    }
}
