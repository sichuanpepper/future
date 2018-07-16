package com.future.experience.aibiying;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 7/14/18.
 */
public class TextJustificationMy {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length < 1 || maxWidth < 1) return res;
        int index = 0, start = index, curLength = 0;
        while (index < words.length) {
            //first check if it's possible to place it in current line, don't forgot add '1' for space between words
            if(curLength + words[index].length() + 1 > maxWidth) {
                //generate current line
                // corner case, current word's length == maxWidth
                if(index == start) {
                    res.add(words[start]);
                    start = ++index;
                } else { //from start(inclusive) to index(exclusive)
                    int endingSpace = maxWidth - curLength;  //the left spaces on the right side.
                    int spaceInWords = index - start - 1;    // the spaces between words.
                    StringBuilder sb = new StringBuilder();
                    if(spaceInWords < 1) {
                        //only one word in the line, add spaces in the end
                        sb.append(words[start]);
                        while (sb.length() < maxWidth) sb.append(' ');
                    } else {
                        int spaceForEach = endingSpace / spaceInWords;  //
                        int extraSpaceFromLeft = endingSpace % spaceInWords;
                        for(int i = start; i < index - 1; i++) {
                            sb.append(words[i]).append(' ');
                            for(int j = 0; j < spaceForEach; j++) sb.append(' ');
                            if(extraSpaceFromLeft-- > 0) sb.append(' ');
                        }
                        sb.append(words[index - 1]);
                    }
                    res.add(sb.toString());
                    start = index;
                }

                curLength = 0;
            } else {
                //one more word will be added
                if(index > start) curLength += 1; // 1 for space between words
                curLength += words[index++].length();
            }
        }
        //add last line
        if(start < words.length) {
            StringBuilder sb = new StringBuilder();
            while (start < words.length - 1) sb.append(words[start++]).append(' ');
            sb.append(words[start]);
            while (sb.length() < maxWidth) sb.append(' ');
            res.add(sb.toString());
        }
        return res;
    }


    public static void main(String[] args) {}
}
