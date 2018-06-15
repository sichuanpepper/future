package com.future.experience.yama;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingfeiy on 3/9/18.
 */
public class ShortestSubSequence {
    /**
     * The problem is find the shortest sub sequence of allTags which include all tags in tagList.
     * You are guaranteed that there's only one unique result.
     *
     * It's similar problem as Problem 76
     *
     * @param tagList
     * @param allTags
     * @return
     */
    public String[] problem(String[] tagList, String[] allTags) {
        if(tagList == null || allTags == null || allTags.length < tagList.length) return new String[0];

        int left = 0, right = 0, start = 0, end = Integer.MAX_VALUE, count = tagList.length;
        Map<String, Integer> map = new HashMap<>();
        for(String tag : tagList) map.put(tag, map.getOrDefault(tag, 0) + 1);

        while (right < allTags.length) {
            if(map.containsKey(allTags[right])) {
                count--;
                map.put(allTags[right], map.get(allTags[right]) - 1);
            }
            right++;
            while (count == 0) {
                if(right - left < end - start) {
                    start = left;
                    end = right;
                }
                if(map.containsKey(allTags[left])) {
                    map.put(allTags[left], map.get(allTags[left]) + 1);
                    count++;
                }
                left++;
            }
        }
        return end == Integer.MAX_VALUE ? new String[0] : Arrays.copyOfRange(allTags, start, end);
    }

    public static void main(String[] args) {
        ShortestSubSequence problem = new ShortestSubSequence();
        String[] res = problem.problem(new String[]{"apple", "orange", "apple"},
                new String[]{"apple", "pear", "pear", "apple", "pear", "orange", "pear", "apple", "apple"});
        for(String str : res) {
            System.out.println(str);
        }
    }
}
