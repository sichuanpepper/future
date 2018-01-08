package com.future.round2;

/**
 * https://leetcode.com/problems/2-keys-keyboard/description/
 Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

 Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
 Paste: You can paste the characters which are copied last time.
 Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted.
 Output the minimum number of steps to get n 'A'.

 Example 1:
 Input: 3
 Output: 3
 Explanation:
 Intitally, we have one character 'A'.
 In step 1, we use Copy All operation.
 In step 2, we use Paste operation to get 'AA'.
 In step 3, we use Paste operation to get 'AAA'.
 Note:
 The n will be in the range [1, 1000].
 * Created by xingfeiy on 1/6/18.
 */
public class Problem650 {
    /**
     * Analyze:
     * The straightforward approach is DFS, each step can generate next two results by (cope + paste) and paste.
     *
     * And another approach is DP, (copy all && paste) is always the fastest way to reach n if this way is available.
     * For prime numbers, the minimum way must equal to itself, likes 3, the minimum steps is 3, why?
     * @param n
     * @return
     */
    public int minSteps(int n) {
        return helper(n, 1, 0, 0);
    }

    private int helper(int n, int curVal, int curPaste, int curStep) {
        if(n == curVal) return curStep;
        if(curVal > n) return Integer.MAX_VALUE;
        int step = helper(n, curVal * 2, curVal, curStep + 2);
        if(step < Integer.MAX_VALUE) return step;
        return helper(n, curVal + curPaste, curPaste, curStep + 1);
    }

}
