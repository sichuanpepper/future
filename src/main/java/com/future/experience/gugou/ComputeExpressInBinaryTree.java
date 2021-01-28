package com.future.experience.gugou;

/**
 * 给一个二叉树。保证每一个节点 ， 要么没有子节点们要么有两个子节点。（就是不存在 node.left == null && node.right != null || node.left != null && node.right == null）
 *
 * 这个二叉树，叶子节点都是int。（可正，可负）。非叶子节点都是要么+，要么-
 *
 * 1.问这个二叉树组成的表达式计算出的值是多少？
 * 2.问如果可以删掉一些节点（但是要保证每个节点要么没有子节点，要么有两个子节点）。最后计算出来的值能否等于N
 * 3. 如果可以，最少要删除几个节点？
 */
public class ComputeExpressInBinaryTree {
    private static class SignTreeNode {
        public String val;

        public boolean isSign;

        public SignTreeNode left;

        public SignTreeNode right;

        public SignTreeNode(String val, boolean isSign) {
            this.val = val;
            this.isSign = isSign;
        }
    }

    /**
     * The problem is, get the left value and right value then do a calculate based the sign of the node, sounds like a post-order traversal.
     * The sign could be + or -
     * Assume the tree is not null.
     *
     * @param root
     * @return
     */
    public int calculate(SignTreeNode root) {
        if(!root.isSign) {
            return Integer.parseInt(root.val);
        }
        int left = calculate(root.left);
        int right = calculate(root.right);
        return root.val == "+" ? left + right : left - right;
    }

    /**
     * The straightforward way is find a sub-tree where the result equals to target.
     * The minimal nodes to be delete, which means find the nearest sub-tree to the root.
     * @param root
     * @param target
     * @return
     */
    public boolean deleteAndEqual(SignTreeNode root, int target) {
        helper(root, target);
        return subRoot != null;
    }

    private SignTreeNode subRoot = null;
    private int helper(SignTreeNode root, int target) {
        if(subRoot != null) return 0;

        if(!root.isSign) {
            return Integer.parseInt(root.val);
        }
        int left = helper(root.left, target);
        int right = helper(root.right, target);
        int val = root.val == "+" ? left + right : left - right;
        if(val == target) {
            subRoot = root;
        }
        return val;
    }

    /**
     * To minimize the deletion, which means find the max sub-tree where the result equals to target.
     * So we can use same way, post-order traversal, this time we should return the count of node with the result.
     * @param root
     * @param target
     * @return
     */
    public int minDelete(SignTreeNode root, int target) {
        subRoot = null;
        int[] res = helper2(root, target);
        return res[1] - res[2];
    }

    //res[0]: result, res[1]: count, res[2]: max
    public int[] helper2(SignTreeNode root, int target) {
        if(!root.isSign) {
            return new int[]{Integer.parseInt(root.val), 1, -1};
        }
        int left[] = helper2(root.left, target);
        int right[] = helper2(root.right, target);
        int val = root.val == "+" ? left[0] + right[0] : left[0] - right[0];
        if(val == target) {
            return new int[]{val, left[1] + right[1] + 1, left[1] + right[1] + 1};
        }
        return new int[]{val, left[1] + right[1] + 1, Math.max(left[2], right[2])};
    }


    public static void main(String[] args) {
        ComputeExpressInBinaryTree p = new ComputeExpressInBinaryTree();
        SignTreeNode root = new SignTreeNode("2", false);
        System.out.println(p.calculate(root));
        root = new SignTreeNode("+", true);
        root.left = new SignTreeNode("-", true);
        root.right = new SignTreeNode("4", false);
        root.left.left = new SignTreeNode("3", false);
        root.left.right = new SignTreeNode("1", false);
        System.out.println(p.calculate(root));
        System.out.println(new ComputeExpressInBinaryTree().deleteAndEqual(root, 2));
        System.out.println(new ComputeExpressInBinaryTree().deleteAndEqual(root, 4));
        System.out.println(new ComputeExpressInBinaryTree().deleteAndEqual(root, 7));
        System.out.println(new ComputeExpressInBinaryTree().minDelete(root, 2));

    }

}
