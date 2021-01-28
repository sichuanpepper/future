package com.future.experience.didi;

import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EasyQuestions {
    public String toLowerCase(String str) {
        return "";
    }

    public String addStrings(String num1, String num2) {
        if(num1 == null || num1.length() < 1 || num1.equals("0")) return num2;
        if(num2 == null || num2.length() < 1 || num2.equals("0")) return num1;

        String res = "";
        int carry = 0, p1 = num1.length() - 1, p2 = num2.length() - 1;
        while(p1 >= 0 || p2 >= 0 || carry > 0) {
            int val1 = (p1 >= 0) ? num1.charAt(p1--) - '0' : 0;
            int val2 = (p2 >= 0) ? num2.charAt(p2--) - '0' : 0;
            int val = val1 + val2 + carry;
            res = Integer.toString(val % 10) + res;
            carry = val / 10;
        }
        return res;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public List<Integer> largestValuesBFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                max = Math.max(max, tmp.val);
                if(tmp.left != null) queue.offer(tmp.left);
                if(tmp.right != null) queue.offer(tmp.right);
            }
            res.add(max);
        }
        return res;
    }
}
