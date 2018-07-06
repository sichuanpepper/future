package com.future.experience.aibiying;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xingfeiy on 6/14/18.
 */
public class TextJustification {
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



    public List<String> fullJustify2(String[] words, int maxWidth) {
        int start = 0, end = start + 1;
        List<String> res = new ArrayList<>();
        while (start < words.length) {
            int count = words[start].length();
            while (end < words.length) {
                //check if it's enough to add end word.
                if(count + 1 + words[end].length() > maxWidth) break;  //add 1 means add 1 space.
                count += words[end++].length() + 1;
            }

            //generate one line
            int countOfWords = end - start - 1;
            //is left justify? countOfWords < 2, or it's last line.
            if(countOfWords < 2 || end >= words.length) {
                StringBuilder sb = new StringBuilder();
                for(int i = start; i < end; i++) {
                    sb.append(words[i]).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                for(int i = sb.length(); i < maxWidth; i++) sb.append(" ");
                res.add(sb.toString());
            } else {
                int forEach = (maxWidth - count) % (countOfWords - 1);
                int left = (maxWidth - count) / (countOfWords - 1);
                StringBuilder sb = new StringBuilder();
                for(int i = start; i < end; i++) {
                    sb.append(words[i]);
                    if(i < end - 1) {
                        for(int j = 0; j < forEach; j++) sb.append(" ");
                        if(left -- > 0) sb.append(" ");
                    }
                }
                res.add(sb.toString());
            }
            start = end;
        }
        return res;
    }
}
