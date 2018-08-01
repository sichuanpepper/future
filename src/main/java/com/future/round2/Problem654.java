package com.future.round2;

import com.future.utils.TreeNode;

/**
 * https://leetcode.com/problems/maximum-binary-tree/description/
 Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

 The root is the maximum number in the array.
 The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 Construct the maximum tree by the given array and output the root node of this tree.

 Example 1:
 Input: [3,2,1,6,0,5]
 Output: return the tree root node representing the following tree:

       6
     /   \
    3     5
    \    /
    2  0
    \
    1
 Note:
 The size of the given array will be in the range [1,1000].

 * Created by xingfeiy on 7/31/18.
 */
public class Problem654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if(start < 0 || end > nums.length - 1 || start > end) return null;
        if(start == end) return new TreeNode(nums[start]);
        int large = start;
        for(int i = start; i <= end; i++) {
            if(nums[i] > nums[large]) large = i;
        }
        TreeNode root = new TreeNode(nums[large]);
        root.left = helper(nums, start, large - 1);
        root.right = helper(nums, large + 1, end);
        return root;
    }
}
