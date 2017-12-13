package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/next-closest-time/description/
 *
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.

 You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

 Example 1:

 Input: "19:34"
 Output: "19:39"
 Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
 It is not 19:33, because this occurs 23 hours and 59 minutes later.
 Example 2:

 Input: "23:59"
 Output: "22:22"
 Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned
 time is next day's time since it is smaller than the input time numerically.

 * Created by someone on 11/1/17.
 */
public class Problem681 {
    /**
     * Analyze:
     * How to simplify the problem? We can convert the time to minutes.
     * @param time
     * @return
     */
    public static String nextClosestTime(String time) {
        if(time == null || time.length() < 1) return time;
        int maxMinutes = 24 * 60;
        String[] tokens = time.split(":");
        int givenMinutes = Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
        Set<Integer> givenDigits = new HashSet<>();
        givenDigits.add(time.charAt(0) - '0');
        givenDigits.add(time.charAt(1) - '0');
        givenDigits.add(time.charAt(3) - '0');
        givenDigits.add(time.charAt(4) - '0');
        int closestMins = Integer.MAX_VALUE;
        int finalMin = 0;
        for(int first : givenDigits) {
            if(first > 2) continue;
            for(int second : givenDigits) {
                if(first == 2 && second >= 4) continue;
                for(int third : givenDigits) {
                    if(third > 5) continue;
                    for(int four : givenDigits) {
                        int tmp = (first * 10 + second) * 60 + third * 10 + four;
                        int min = tmp > givenMinutes ? tmp - givenMinutes : (maxMinutes - givenMinutes) + tmp;
                        if(min != 0 && min < closestMins) {
                            finalMin = tmp;
                            closestMins = min;
                        }
                    }
                }
            }
        }
        int hour = finalMin / 60;
        int min = finalMin % 60;


        return (hour < 9 ? ("0" + hour) : hour) + ":" + (min < 9 ? ("0" + min) : min);
    }


    public static void main(String[] args) {
        System.out.println(nextClosestTime("23:59"));
        System.out.println(nextClosestTime("19:34"));
        System.out.println(nextClosestTime("11:11"));
        System.out.println(nextClosestTime("00:00"));
        System.out.println(nextClosestTime("01:00"));
    }
}
