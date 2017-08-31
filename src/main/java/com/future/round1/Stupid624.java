package com.future.round1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xingfeiy on 6/22/17.
 */
public class Stupid624 {
    public static int maxDistance(List<List<Integer>> arrays) {
        //
        int[] min = new int[]{0, Integer.MAX_VALUE};
        int[] max = new int[]{0, Integer.MIN_VALUE};
        for(List<Integer> array : arrays) {
            int minMax = Math.abs(array.get(0) - max[0]);
            if(minMax < min[1]) {
                min[0] = array.get(0);
                min[1] = minMax;
            }

            int maxMin = Math.abs(array.get(array.size() - 1) - min[0]);
            if(maxMin > max[1]) {
                max[0] = array.get(array.size() - 1);
                max[1] = maxMin;
            }
        }
        return Math.max(min[1], max[1]);
    }

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 5));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(3, 4));
        List<List<Integer>> arrays = new ArrayList<>();
        arrays.add(l1);
        arrays.add(l2);
        maxDistance(arrays);
    }
}
