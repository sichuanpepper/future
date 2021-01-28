package com.future.experience.gugou;

import com.future.utils.TreeNode;

/**
 * 一个二叉树，把所有子节点的value挪到根节点需要多少move。
 * move 的定义： 子节点的一个value，移动到上一层算一个move，再移动到上一层，又算一个move。
 * follow up， 如果要求每个子节点必须保留value=1，其余都move到根节点，需要多少move
 */
public class MoveValuesToRoot {

    /**
     * Post-order traversal
     * If left child is not null, recursive(left) +1
     * If right child is not null, recursive(right) + 1
     * @param root
     * @return
     */
    public int stepsToMove(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int res = 0;
        if(root.left != null) {
            res += (stepsToMove(root.left) + 1);
        }
        if(root.right != null) {
            res += (stepsToMove(root.right) + 1);
        }
        return res;
    }

    /**
     * Questions to clarify:
     * Can we assume all nodes have positive value? if we can't, how to guarantee all nodes expect root have value 1?
     *    2
     *  /  \          ==> 0 move
     * 1    1
     *
     *        2       (root)
     *      1   1     (1l, 1r)
     *     2          (2l)
     *  2l -> 1l, 1l.val = 2
     *  1l -> root, root.val = 2
     *  Total 2 moves
     *
     *         2
     *       0   1
     *      2
     *  Total 1 move
     *  We can use same approach as the previous problem, instead returns moves only, we return moves and the number collected from sub tree.
     *  For any node, check its children one by one, if child's value + value collected > 1, a move is required.
     * @param root
     * @return
     */
    public int stepsToMoveFollowup(TreeNode root) {
        int[] res = helper(root);
        return res[0];
    }

    //int[0] moves, int[1] value collected from subtree
    private int[] helper(TreeNode root) {
        if(root == null) {
            return new int[2];
        }
        int res = 0, collected = 0;
        if(root.left != null) {
            int[] left = helper(root.left);
            res += left[0];
            collected = Math.max(0, root.left.val < 1 ? left[1] - 1 : left[1]);
            if(root.left.val > 1 || left[1] > 0) {
                res++;
            }
        }
        if(root.right != null) {
            int[] right = helper(root.right);
            res += right[0];
            collected += Math.max(0, root.right.val < 1 ? right[1] - 1 : right[1]);
            if(root.right.val > 1 || right[1] > 0) {
                res++;
            }
        }
        return new int[]{res, collected};
    }

    public static void main(String[] args) {
        MoveValuesToRoot p = new MoveValuesToRoot();
        TreeNode root = new TreeNode(2);
        System.out.println(p.stepsToMove(root));
        root.left = new TreeNode(0);
        System.out.println(p.stepsToMove(root));
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(p.stepsToMove(root));
        System.out.println(p.stepsToMoveFollowup(root));
    }
}
