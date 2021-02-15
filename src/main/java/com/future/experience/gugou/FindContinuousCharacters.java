package com.future.experience.gugou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindContinuousCharacters {
    /**
     * 找出string中连续出现次数大于等于3次的字母，返回他们的起始点和中点
     * 例如heeelloooo 返回 [[1, 3], [6, 9]]
     * @param str
     * @return
     */
    public List<List<Integer>> solution(String str) {
        List<List<Integer>> res = new ArrayList<>();
        if(str == null || str.length() < 1) return res;

        int p1 = 0, p2 = 0;
        while (p2 < str.length()) {
            if(str.charAt(p2) == str.charAt(p1)) {
                p2++;
            } else {
                if((p2 - p1) >= 3) {
                    List<Integer> subRes = Arrays.asList(new Integer[]{p1, p2 - 1});
                    res.add(subRes);
                }
                p1 = p2;
            }
        }
        if((p2 - p1) >= 3) {
            List<Integer> subRes = Arrays.asList(new Integer[]{p1, p2 - 1});
            res.add(subRes);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FindContinuousCharacters().solution("heeelloooo"));
        System.out.println(new FindContinuousCharacters().solution(""));
        System.out.println(new FindContinuousCharacters().solution("aaaccbcccccccd"));
    }
}
