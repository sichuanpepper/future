package com.future.experience.aibiying;

import java.util.HashMap;
import java.util.Map;

/**
 * https://en.wikipedia.org/wiki/Collatz_conjecture
 *
 * Created by xingfeiy on 6/14/18.
 */
public class CollatzConjecture {
    //key: num, value: steps
    private Map<Integer, Integer> map = new HashMap<>();
    private int stepOfNum(int num) {
        if(num == 1) return 1;
        if(map.containsKey(num)) return map.get(num);
        if(num % 2 == 0) return stepOfNum(num / 2) + 1;
        return stepOfNum(num * 3 + 1) + 1;
    }

    public int findMaxStep(int num) {
        int max = 0;
        for(int i = 1; i <= num; i++) {
            int steps = stepOfNum(i);
            max = Math.max(max, steps);
            map.put(i, steps);
        }
        return max;
    }
}
