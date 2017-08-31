package com.future.foundation.db;

import com.future.utils.DisplayUtils;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * Created by xingfeiy on 5/30/17.
 */
public class MaximumSubarray {
    /**
     * Analyse:
     * There are a lot ways to sum elements within array, we want to find the largest sum one, looks like a optimal problem.
     *
     * has optimal substructure?
     * We say that a problem exhibits optimal substructure if an optimal solution to the problem
     * contains within it optimal solutions to subproblems.
     * As example, [4,-1,2,1] is the optimal solution to the original array, if we take 1 away, is [4,-1,2] the optimal
     * solution to the array [-2,1,-3,4,-1,2]? The answer is yes.
     *
     * has overlapping subproblems?
     * When a recursive algorithm revisits the same problem over and over again,
     * we say that the optimization problem has overlapping subproblems.
     * (Thinking about divide & conquer approach, it usually generates brand-new problems)
     * Let's go back to the problem,
     * maxsum[0] = -2;
     * maxsum[1] = maxsum[0] + array[1] ? ...   ==> 1
     * maxsum[2] = maxsum[1] + array[2] ? ...   ==> 1
     * maxsum[3] = maxsum[2] + array[3] ? ...   ==> 4
     * ...
     * maxsum[n] is the largest sum in array[1...n]
     * maxsum[k] = ???
     * maxsum[0] = -2;
     * maxsum[1] = max{maxsum[0] + array[1], array[1]} | array[1] > 0
     * maxsum[2] = max{maxsum[1]} | array[2] < 0, we know the largest sum is maxsum[1] in array[-2, 1, -3], but it seems
     * that there's something missed, it matter to know where is the largest sum from.
     *
     * So, we may need two dimensions to describe this problem.
     * maxsum[k, m] where the k is the kth element in array, and m is the mth element where the largest sum comes from.
     * maxsum[0, 0] = -2;
     * maxsum[1, 1] = 1;
     * maxsum[2, 1] = 1;
     * maxsum[3, 3] = 4;
     * ...
     * maxsum[k, m] = max{maxsum[k - 1, m] + array[k], array[k]} | array[k] >= 0 (we may reset the m)
     * maxsum[k, m] = maxsum[k - 1, m] | array[k] < 0
     *
     * Let's look the solution one based on the analyse above first.
     * ... 10 minutes passed, I can't finish the implementation since the above solution is not correct or not good.
     *
     * Let's back to the problem to see what's wrong here.
     * The problem is I can't fill the two-dimensions fully, so I ask myself, are there two factors to describe problem?
     * Actually, there's one, which is kth, for maxsum[k], we only care about the one position where the largest sum comes from.
     * So we can optimize the solution in space as:
     * maxsum[0...n] and max_position, and
     * maxsum[k] = max{maxsum[k - 1] + array[k], maxsum[max_position]} | array[k] >= 0;
     * maxsum[k] = max{maxsum[max_position], array[k] } | array[k] < 0;
     * Let's implement it in solution2.
     *
     * Ok, the solution2 works, can we optimize it? The answer is yes.
     * In the solution2, we care about one previous element actually, the max sum in the previous array, so we can use
     * a constant instead of an array, likes what we implement in solution3.
     *
     *
     * @param nums
     * @return
     */
    public static int solution1(int[] nums) {
        int[][] results = new int[nums.length][nums.length];
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums.length; j++) {
                results[i][j] = Integer.MIN_VALUE;
            }
        }

        results[0][0] = -2;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] >= 0) {
                int maxnum = Math.max(results[i - 1][i - 1], nums[i]);
//                int maxpos =
                results[i][i] = Math.max(results[i - 1][i - 1], nums[i]);

            }
        }

        return 0;
    }

    public static int solution2(int[] nums) {
        int[] results = new int[nums.length];
        results[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            results[i] = Integer.MIN_VALUE;
        }

        int maxPos = 0;
        for(int i = 1; i < nums.length; i++) {
            results[i] = Math.max(results[i - 1] + nums[i], nums[i]);
            if(results[i] > results[maxPos]) {
                maxPos = i;
            }
        }
        DisplayUtils.printArray(results);
        return results[maxPos];
    }

    public static int solution3(int[] nums) {
        int maxsum = nums[0];
        int sumHere = nums[0];
        for(int i = 1; i < nums.length; i++) {
            sumHere = Math.max(sumHere + nums[i], nums[i]);
            maxsum = Math.max(maxsum, sumHere);
        }
        return maxsum;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution2(array));
        System.out.println(solution3(array));
    }

}
