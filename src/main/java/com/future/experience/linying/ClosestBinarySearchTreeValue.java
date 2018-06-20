package com.future.experience.linying;

import com.future.utils.DisplayUtils;
import com.future.utils.TreeNode;

import java.util.*;

/**
 * Created by xingfeiy on 6/18/18.
 */
public class ClosestBinarySearchTreeValue {
    /**
     * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
     * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
     * Analyze:
     *  - Binary search to find the insert position of x.
     *  - If k is odd, select the elements from both left and right, here, left = right = k / 2
     *  - If k is Even, left = k / 2, right = k / 2 - 1
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        // if x existed in arr, return the index of x in arr.
        // if x doesn't exist in arr, return (-index) - 1, where index is the position of first element greater than x, or length of arr.
        int insertPos = Arrays.binarySearch(arr, x);
        if(insertPos < 0) insertPos = -(insertPos + 1);
        int left = insertPos - 1, right = insertPos;
        while (k-- > 0) {
            if(left < 0 || (right < arr.length && Math.abs(arr[left] - x) > Math.abs(arr[right] - x))) {
                right++;
            } else {
                left--;
            }
        }
        for(int i = left + 1; i <= right; i++) res.add(arr[i]);
        return res;
    }

    /**
     * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

     Note: Given target value is a floating point. You may assume k is always valid, that is: k ≤ total nodes.
     You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

     Follow up: Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

     Analyze:
     The straightforward way is we maintain a queue with size k and do in-order traversal
     - If current size of queue is smaller than k, just insert the element.
     - If current size of queue is equal to or larger than k, compare current difference and the peek's difference.
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Queue<Integer> queue = new LinkedList<>();
        helper(root, target, k, queue);
        return (List<Integer>)queue;
    }

    private void helper(TreeNode node, double target, int k, Queue<Integer> queue) {
        if(node == null) return;
        helper(node.left, target, k, queue);
        if(queue.size() < k) {
            queue.offer(node.val);
        } else {
            if(Math.abs(queue.peek() - target) > Math.abs(node.val - target)) {
                queue.poll();
                queue.offer(node.val);
            } else {
                return;
            }
        }
        helper(node.right, target, k, queue);
    }



    public static void main(String[] args) {
        ClosestBinarySearchTreeValue p = new ClosestBinarySearchTreeValue();
        DisplayUtils.printList(p.closestKValues(TreeNode.getBSTSample(), 0.0, 3)); //1, 2, 3
        DisplayUtils.printList(p.closestKValues(TreeNode.getBSTSample(), 2.3, 3)); //1, 2, 3
        DisplayUtils.printList(p.closestKValues(TreeNode.getBSTSample(), 2.7, 3)); //2, 3, 4
        DisplayUtils.printList(p.closestKValues(TreeNode.getBSTSample(), 21.7, 6)); //2, 3, 4
        DisplayUtils.printList(p.closestKValues(TreeNode.getBSTSample(), 21.7, 7)); //2, 3, 4
    }
}
