package com.future.round2;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  * https://leetcode.com/problems/text-justification/description/
 *
 Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when
 necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words,
 the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Note: Each word is guaranteed not to exceed L in length.

 * Created by xingfeiy on 6/14/18.
 */
public class Problem68 {
    /**
     * Analyze:
     * - Create char array with length maxWidth, and initial insertPos as 0.
     * - If insertPos + cur_word.length < maxWidth
     *      - Insert characters of cur_word into char array.
     *      - Update insertPos
     *      - count_space++
     * - If insertPos + cur_word.length > maxWidth
     *      - left spaces = maxWidth - (insertPos + 1)
     *          - Evenly distribute left space (not last line)
     *              - time_each_space = left_spaces / count_space
     *              - extra_space = left_spaces % count_space (using these spaces from left to side)
     *          - reset char array, insertPos, count_space
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length < 1 || maxWidth < 1) return res;
        int insertPos = 0, wordPos = 0;
        char[] array = new char[maxWidth];
        Arrays.fill(array, ' ');
        while (wordPos < words.length) {
            if(insertPos + words[wordPos].length() <= maxWidth) {
                for(char ch : words[wordPos].toCharArray()) {
                    array[insertPos++] = ch;
                }
                insertPos += 2; //one for space.
                wordPos++;
            } else {
                int countOfSpaces = 0;
                //the insertPos may larger than maxWidth
                insertPos = insertPos >= maxWidth ? maxWidth : insertPos;
                //count valid space
                for(int i = 0; i < maxWidth - 1; i++) {
                    if(array[i] == ' ' && array[i + 1] != ' ') countOfSpaces++;
                }
                if(countOfSpaces == 0) {
                    res.add(new String(array));
                } else {
                    int leftSpaces = maxWidth - (insertPos + 1);
                    int timesEach = leftSpaces / countOfSpaces;
                    int extraSpaces = leftSpaces % countOfSpaces;
                    //generate current line
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < insertPos - 1; i++) {
                        if(array[i] == ' ') {
                            sb.append(' ');
                            for(int j = 0; j < timesEach; j++) sb.append(' ');
                            if(extraSpaces-- > 0) sb.append(' ');
                        } else {
                            sb.append(array[i]);
                        }
                    }
                    res.add(sb.toString());
                }

                //start a new line
                Arrays.fill(array, ' ');
                insertPos = 0;
                countOfSpaces = 0;
            }
        }
        //add last line
        res.add(new String(array));
        return res;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        Problem68 p = new Problem68();
        for(String str : p.fullJustify(words, 16)) {
            System.out.println(str);
        }
    }
}
