package com.future.experience.aibiying;

/**
 Provide a set of positive integers (an array of integers). Each integer represents number of nights user
 request on Airbnb.com. If you are a host, you need to design and implement an algorithm to find out the
 maximum number a night you can accommodate. The constrain is that you have to reserve at least one
 day between each request, so that you have time to clean the room.
 Given a set of numbers in an array which represent number of consecutive days of AirBnB reservation
 requested, as a host, pick the sequence which maximizes the number of days of occupancy, at the same
 time, leaving at least 1 day gap in between bookings for cleaning. Problem reduces to finding max-sum
 of non-consecutive array elements.
 // [5, 1, 1, 5] => 10
 The above array would represent an example booking period as follows -
 // Dec 1 – 5
 // Dec 5 – 6
 // Dec 6 – 7
 // Dec 7 - 12
 The answer would be to pick dec 1-5 (5 days) and then pick dec 7-12 for a total of 10 days of occupancy,
 at the same time, leaving at least 1 day gap for cleaning between reservations.
 Similarly,

 // [3, 6, 4] => 7
 // [4, 10, 3, 1, 5] => 15

 * Created by xingfeiy on 6/13/18.
 */
public class MaxNightAccommodate {
    /**
     * f(n) = max{f(n - 2) + nums[n], f(n - 1)}
     * @param nums
     * @return
     */
    public int mySolution(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        if(nums.length < 2) return nums[0];
        int p1 = nums[0], p2 = Math.max(nums[0], nums[1]);
        for(int i = 2; i < nums.length; i++) {
            int curMax = Math.max(nums[i] + p1, p2);
            p1 = p2;
            p2 = curMax;
        }
        return p2;
    }

    public int max(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int f1 = nums[0]; // exclude max
        int f2 = Math.max(nums[0], nums[1]); // max so far
        for (int i = 2; i < n; i++) {
            int f = Math.max(f1 + nums[i], f2);
            f1 = f2;
            f2 = f;
        }
        return f2;
    }

    public static void main(String[] args) {
        MaxNightAccommodate m = new MaxNightAccommodate();
        System.out.println(m.mySolution(new int[]{5}));
        System.out.println(m.mySolution(new int[]{5, 1}));
        System.out.println(m.mySolution(new int[]{1, 5}));
        System.out.println(m.mySolution(new int[]{5, 1, 1, 5})); //10
        System.out.println(m.mySolution(new int[]{3, 6, 4})); //7
        System.out.println(m.mySolution(new int[]{4, 10, 3, 1, 5})); //15
        System.out.println(m.mySolution(new int[]{4, 10, 3, 1, 100})); //15
    }
}
