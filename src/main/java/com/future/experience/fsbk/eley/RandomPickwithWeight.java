package com.future.experience.fsbk.eley;

import java.util.Arrays;
import java.util.Random;

public class RandomPickwithWeight {
    private int[] accWeight;

    private Random random = new Random();

    public RandomPickwithWeight(int[] w) {

        accWeight = new int[w.length];
        accWeight[0] = w[0];
        for(int i = 1; i < w.length; i++) {
            accWeight[i] = accWeight[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int num = random.nextInt(accWeight[accWeight.length - 1] ) + 1;   //not nextInt(sum + 1), should be nextInt(sum) + 1;
        int pos = Arrays.binarySearch(accWeight, num);
        pos = pos < 0 ? (-pos - 1) : pos;
        return pos;
    }
}
