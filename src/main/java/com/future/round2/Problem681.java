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
     * 1. Find the closest hour, then find the closest minute.
     * 2. If the minute is 59 in the given time, find the next closest hour can be produced, otherwise, return current hour.
     * 3. Use the 4 digital to generate valid hours and minutes.
     *
     * @param time
     * @return
     */
    public static String nextClosestTime(String time) {

//        int[] digital = new int[]{time.charAt(0) - '0', time.charAt(1) - '0', time.charAt(3) - '0', time.charAt(4) - '0'};
//
//        //find all possibilities.
//        Set<Integer> validHours = new HashSet<>();
//        Set<Integer> validMinutes = new HashSet<>();
//        for(int i : digital) {
//            for(int j : digital) {
//                if(i * 10 + j < 60) {
//                    validMinutes.add(i * 10 + j);
//                }
//                if(i * 10 + j < 24) {
//                    validHours.add(i * 10 + j);
//                }
//            }
//        }
//        int givenMin = (digital[0] * 10 + digital[1]) * 60 + digital[2] * 10 + digital[3];
//        int givenHour = digital[0] * 10 + digital[1];
//        int diff = Integer.MAX_VALUE;
//        int minH = 0;
//        int minM = 0;
//        for(int h : validHours) {
//            for(int m : validMinutes) {
//                int min = h <  givenHour? (h + 23 - givenHour) * 60 + m : h * 60 + 24;
//                if(min != givenMin && min - givenMin < diff) {
//                    diff = min - givenMin;
//                    minH = h;
//                    minM = m;
//                }
//            }
//        }
//        return (minH > 9 ? minH : "0" + minH) + ":" + (minM > 9 ? minM : "0" + minM);
        return "";
    }


    public static void main(String[] args) {
//        System.out.println(nextClosestTime("23:59"));
        System.out.println(nextClosestTime("19:34"));
    }
}
