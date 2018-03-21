package com.future.oa.amazon;

import java.util.*;

/**
 * Created by xingfeiy on 3/11/18.
 */
public class CountWords {
    public List<String> problem(String str, List<String> stopWords) {
        List<String> res = new ArrayList<>();
        if(str == null || str.length() < 1) return res;
        Map<String, Integer> maps = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for(String word : str.split(" ")) {
            if(!stopWords.contains(word)) {
                int count = maps.getOrDefault(word, 0) + 1;
                max = Math.max(max, count);
                maps.put(word, count);
            }
        }


        for(Map.Entry<String, Integer> entry : maps.entrySet()) {
            if(entry.getValue() == max) res.add(entry.getKey());
        }
        return res;
    }


}
