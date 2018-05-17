package com.future.experience.facebook;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * There are some cities with population, randomly pick up a city based on its population.
 * example,
 * city1->100
 * city2->600
 * city3->300
 *
 * city2 should have the highest possibility to be pick up, city3 next, and city1
 *
 * Created by xingfeiy on 5/16/18.
 */
public class RandomBasedOnPopulation {
    /**
     *
     * @param populations key -> city, value -> population
     * @return key -> city, value -> the times of picked up
     */
    public Map<String, Integer> pickup(Map<String, Integer> populations, int times) {
        int total = 0;
        int[] array = new int[populations.size()];
        String[] cities = new String[populations.size()];
        int i = 0;
        for(Map.Entry<String, Integer> entry : populations.entrySet()) {
            array[i] = ((i > 0) ? array[i - 1] : 0) + entry.getValue();
            total += entry.getValue();
            cities[i++] = entry.getKey();
        }

        Map<String, Integer> res = new HashMap<>();
        for(i = 0; i < times; i++) {
            int random = new Random().nextInt(total);
            int found = findInsertPos(array, random) + 1;
            res.put(cities[found], res.getOrDefault(cities[found], 0) + 1);
        }
        return res;
    }

    private int findInsertPos(int[] nums, int val) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == val) {
                return mid;
            } else if(nums[mid] > val) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if(val < nums[start]) return start - 1;
        return (val < nums[end]) ? end - 1 : end;
    }


    public static void main(String[] args) {
        Map<String, Integer> populations = new HashMap<>();
        populations.put("BeiJing", 10);
        populations.put("ShangHai", 10);
        populations.put("SanJose", 10);
        populations.put("NewYork", 10);
        populations.put("MyHome", 10);
        RandomBasedOnPopulation p = new RandomBasedOnPopulation();
        Map<String, Integer> res = p.pickup(populations, 10000000);
        for(Map.Entry<String, Integer> entry : res.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}
