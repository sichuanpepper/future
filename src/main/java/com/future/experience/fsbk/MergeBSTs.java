package com.future.experience.fsbk;

import com.future.utils.DisplayUtils;
import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * We are given two BSTs, output the elements of both BSTs in sorted form.
 *
 * example:
 *      4          7
 *    /  \       /  \
 *   3   5      6    8
 * output: 3, 4, 5, 6, 7, 8
 * Created by xingfeiy on 4/30/18.
 */
public class MergeBSTs {
    public List<Integer> merge(TreeNode bst1, TreeNode bst2) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        while (bst1 != null) {
            stack1.push(bst1);
            bst1 = bst1.left;
        }

        while (bst2 != null) {
            stack2.push(bst2);
            bst2 = bst2.left;
        }

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if(stack1.isEmpty() || (!stack2.isEmpty() && stack1.peek().val > stack2.peek().val)) {
                TreeNode tmp = stack2.pop();
                res.add(tmp.val);
                tmp = tmp.right;
                while (tmp != null) {
                    stack2.push(tmp);
                    tmp= tmp.left;
                }
            } else if(stack2.isEmpty() || stack1.peek().val <= stack2.peek().val) {
                TreeNode tmp = stack1.pop();
                res.add(tmp.val);
                tmp = tmp.right;
                while (tmp != null) {
                    stack1.push(tmp);
                    tmp = tmp.left;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode bst1 = new TreeNode(4);
        bst1.left = new TreeNode(3);
        bst1.right = new TreeNode(5);
        TreeNode bst2 = new TreeNode(7);
        bst2.left = new TreeNode(6);
        bst2.right = new TreeNode(8);
        MergeBSTs mergeBSTs = new MergeBSTs();
        DisplayUtils.printList(mergeBSTs.merge(bst1, bst2));
    }
}
