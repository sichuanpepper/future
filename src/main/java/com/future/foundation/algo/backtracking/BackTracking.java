package com.future.foundation.algo.backtracking;

import com.future.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Backtracking is an algorithm for finding all solutions, notably constraint satisfaction problems.
 * That incrementally generate the candidates and abandons a candidate as soon as it determines that the candidate can't complete the solution.
 *
 * What kind of problem can be solved by back tracking?
 * - Subsets, permutations, combinations
 *
 * Most times, the back tracking problem can be represent by a tree, then do DFS to find the answers.
 *
 * Summarize:
 * - the helper function of back tracking, actually moves to directions, vertical and horizontal,
 *   the recursive goes to vertical, and the for loop goes to horizontal.
 * - the condition to end recursive(vertical traversal) could be:
 *      - Sum to certain value, or grater/smaller than certain value.
 *      - Reached a null node in the tree, in array, the current index beyond the array bound.
 * - the start point of for loop, we care about it because the recursive call
 *      - always starts from 0
 *      - starts from given point
 * - duplicated elements, most time, sort is required
 *      - in vertical way, the current index always equals to start if start position is presented.
 *      - in horizontal way, simply check current element and (current - 1)
 *
 *  Template:
 *  helper(int[] nums, int start, List curRes, List res) {
 *      - if we need process curRes
 *
 *      - the condition to end recursive
 *
 *      - for loop
 *          - any condition such as remove duplicated
 *          - add to curRes
 *          --> recursive call (go vertical)
 *          -- remove last element from curRes (take a step back, and go horizontal)
 *  }
 * Created by xingfeiy on 6/5/18.
 */
public class BackTracking {

    /**
     * https://leetcode.com/problems/subsets/description/
     * Analyze:
     * This problem, we are given an integer list, and we want to find all subsets.
     * - For any array problem, keep two things in mind, does it contain duplicated elements? Is it sorted?
     * We start from simple, assume there's no duplicated.
     * The order doesn't matter for this problem.
     * Let's see how can we use a tree to represent this problem, we use a simple case [1, 2, 3]
     *              nil
     *         /     |     \
     *        1      2      3
     *      /  \    /
     *     2    3  3
     *    /
     *   3
     *
     * For this subsets problem, it's easy to see we have to store the subsets at each nodes.
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 1) return res;
        subsetsHelper(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void subsetsHelper(int[] nums, int start, List<Integer> cur, List<List<Integer>> res) {
        //end the recursion and handle current res.
        res.add(new ArrayList<>(cur)); //do deep copy if needed.
        if(start >= nums.length) return;

        //the start position, from 0 or given position?
        // go to recursion that means go to next level of the tree, it's easy to see we don't have to traversal previous elements.
        for(int i = start; i < nums.length; i++) {
            cur.add(nums[i]);
            subsetsHelper(nums, i + 1, cur, res);
            //back...
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * Analyze:
     * It's the follow up question, let's say if the duplicate is possible and the results must not contains duplicate subsets.
     * Most times, sort an array is an efficient way to solve the duplication issue.
     *
     * Let's still image this problem as tree as the above problem.
     *              nil
     *         /     |     \
     *        2      2      3
     *      /  \      \
     *     2    3      3
     *    /
     *   3
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 1) return res;
        Arrays.sort(nums);
        subsetsDupHelper(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void subsetsDupHelper(int[] nums, int start, List<Integer> cur, List<List<Integer>> res) {
        res.add(new ArrayList<>(cur));
        if(start >= nums.length) return;
        for(int i = start; i < nums.length; i++) {
            //going to vertical, i always equals start, so the duplicated is allowed in vertical.
            //going to horizontal, i may greater than start, so the duplicated is not allowed in horizontal.
            if(i > start && nums[i] == nums[i - 1]) continue;
            cur.add(nums[i]);
            subsetsDupHelper(nums, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * https://leetcode.com/problems/permutations/
     * Analyze:
     * Let's use same way, imagine the problem as a tree.
     *                           nil
     *                 /          |          \
     *                1           2           3
     *             /    \      /    \ ...
     *            2      3    1     3 ...
     *           /      /    /     /  ...
     *          3      2    3     1   ...
     *
     *  From the above tree, we know:
     *  - The recursion is end while encounter the leafs
     *  - Add current result into final result while encounter the leaf.
     *  - For each recursion, traversal the elements which haven't visited.
     * @param nums
     * @return
     */
    public List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 1) return res;
        permutationsHelper(nums, new ArrayList<>(), res);
        return res;
    }

    /**
     * No start position here since we have to traversal the element from 0 for each recursion.
     * @param nums
     * @param cur
     * @param res
     */
    private void permutationsHelper(int[] nums, List<Integer> cur, List<List<Integer>> res) {
        if(cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(cur.contains(nums[i])) continue;
            cur.add(nums[i]);
            permutationsHelper(nums, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * https://leetcode.com/problems/permutations-ii/description/
     *
     *                           nil
     *                 /          |          \
     *                1           1           3
     *             /    \      /    \ ...
     *            1      3    1     3 ...
     *           /      /    /     /  ...
     *          3      1    3     1   ...
     *  It's easy to see, the same elements in same level must have same sub trees.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 1) return res;
        Arrays.sort(nums);
        //since the given nums may contains duplicate, we can't use same way as above to detect if one element is visited or not.
        boolean[] visited = new boolean[nums.length];
        permuteUniqueHelper(nums, new ArrayList<>(), res, visited);
        return res;
    }

    private void permuteUniqueHelper(int[] nums, List<Integer> cur, List<List<Integer>> res, boolean[] visited) {
        if(cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            //the duplicates are not allowed in same level.
            if(i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            if(visited[i]) continue;
            cur.add(nums[i]);
            visited[i] = true;
            permuteUniqueHelper(nums, cur, res, visited);
            cur.remove(cur.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * https://leetcode.com/problems/combination-sum/description/
     * ex, Input: candidates = [2,3,6,7], target = 7
     * Analyze:
     * Same as above, let's try to imagine the problem as a tree
     *                        nil
     *             /       /        \     \
     *            2       3         6      7
     *     /   /    \  \  ...
     *    2   3     6   7 ...
     *  // \\    ...
     * (2,3,6,7) ...
     *
     * We can see:
     *  - the recursion is end only if current sum is larger than target since each element can be used infinity times.
     *  - the current result will be added into final results if current sum equals target.
     *  - to avoid duplicates, traversal elements from new position in each recursion.
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length < 1 || target < 0) return res;
        combinationSumHelper(candidates, 0, new ArrayList<>(), target, res);
        return res;
    }

    private void combinationSumHelper(int[] candidates, int start, List<Integer> curList, int target, List<List<Integer>> res) {
        if(target < 0) return;
        if(target == 0) {
            res.add(new ArrayList<>(curList));
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            curList.add(candidates[i]);
            combinationSumHelper(candidates, i, curList, target - candidates[i], res);
            curList.remove(curList.size() - 1);
        }
    }

    /**
     * https://leetcode.com/problems/combination-sum-ii/description/
     *
     * The above problem can use any elements in infinity times, this problem, each element can be used only once.
     * And no duplicated combinations are allowed in results.
     * ex. Input: candidates = [1,2,2,3,5], target = 5
     * returns [1,2,2], [2,3], [5]
     *                                 nil
     *                  /               |
     *                 1                2
     *           /   /    \  \
     *          2   2     3   5
     *        /  \
     *       2   2
     *
     *  As we can see:
     *  - the recursion is end if current sum equals target or all elements are visited.
     *  - duplicates are allowed in vertical way, but not allowed in horizontal way(same level, which means in same recursion call).
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length < 1 || target < 0) return res;
        Arrays.sort(candidates);
        combinationSum2Helper(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void combinationSum2Helper(int[] candidates, int start, int target, List<Integer> curList, List<List<Integer>> res) {
        if(target == 0) {
            res.add(new ArrayList<>(curList));
            return;
        }
        if(target < 0) return;

        for(int i = start; i < candidates.length; i++) {
            if(i > start && candidates[i] == candidates[i - 1] ) continue;
            curList.add(candidates[i]);
            combinationSum2Helper(candidates, i + 1, target - candidates[i], curList, res);
            curList.remove(curList.size() - 1);
        }
    }


    /**
     * https://leetcode.com/problems/palindrome-partitioning/description/
     Given a string s, partition s such that every substring of the partition is a palindrome.

     Return all possible palindrome partitioning of s.

     Example:

     Input: "aab"
     Output:
     [
     ["aa","b"],
     ["a","a","b"]
     ]
     *
     * Analyze:
     * Actually, it's a subsets problem, here we just need return the palindrome sebsets.
     * Or, it's similar problem as combination sum, instead of sums to a target, here we want to find palindrome combination.
     *
     *                  nil
     *          /        |       \
     *         a         a        b
     *      /    \      /
     *     a     b     b
     *    /
     *   b
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() < 1) return res;
        partitionHelper(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void partitionHelper(String s, int start, List<String> cur, List<List<String>> res) {
        if(start >= s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for(int i = start; i < s.length(); i++) {
            if(isPalindrome(s.substring(start, i + 1))) {
                cur.add(s.substring(start, i + 1));
                partitionHelper(s, i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }

        }
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        BackTracking b = new BackTracking();
        List<List<Integer>> res = b.subsetsDup(new int[]{2, 2, 3});
        for(List<Integer> r : res) {
            DisplayUtils.printList(r);
        }
    }

}
