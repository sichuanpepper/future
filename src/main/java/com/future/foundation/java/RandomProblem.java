package com.future.foundation.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by xingfeiy on 4/5/18.
 */
public class RandomProblem {
    private static Map<String, Integer> populationMap = new HashMap<>();

    static {
        populationMap.put("China", 1300000000);
        populationMap.put("India", 1000000000);
        populationMap.put("USA", 300000000);
        populationMap.put("Canada", 10000000);
        populationMap.put("Mexico", 8000000);
    }

    /**
     * We are going to implement a function to generate a country list randomly, larger population has higher possibility.
     */
    public static void randomPickup() {
        String[] countries = new String[populationMap.size()];
        long[] range = new long[populationMap.size()];
        int index = 0;
        for(Map.Entry<String, Integer> entry : populationMap.entrySet()) {
            countries[index] = entry.getKey();
            range[index] = index > 0 ? range[index - 1] + entry.getValue() : entry.getValue();
            index++;
        }
        Map<String, Integer> counter = new HashMap<>();
        for(int i = 0; i < 10000000; i++) {
//            long randomVal = random.nextLong();
//            randomVal = randomVal & range[range.length - 1];
//            long randomVal = ThreadLocalRandom.current().nextLong(range[range.length - 1]);
            long randomVal = (long)(Math.random() * (range[range.length - 1] + 1));
            for(int j = 0; j < range.length; j++) {
                if(randomVal <= range[j]) {
                    counter.put(countries[j], counter.getOrDefault(countries[j], 0) + 1);
                    break;
                }
            }
        }

        for(Map.Entry<String, Integer> entry : counter.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

    }

    public static void main(String[] args) {
        randomPickup();
    }
}
