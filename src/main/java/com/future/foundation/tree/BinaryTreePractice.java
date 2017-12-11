package com.future.foundation.tree;

import com.future.utils.BTreePrinter;
import com.future.utils.DisplayUtils;
import com.future.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingfeiy on 12/10/17.
 */
public class BinaryTreePractice {
    /**
     * Find the lowest common ancestor in binary search tree.
     * Assuming nodeA and nodeB are always existed and head is not null.
     *
     * @param head
     * @return
     */
    public static TreeNode findLCAInBST(TreeNode head, TreeNode nodeA, TreeNode nodeB) {
        if(head.val > nodeA.val && head.val > nodeB.val) return findLCAInBST(head.left, nodeA, nodeB);
        if(head.val < nodeA.val && head.val < nodeB.val) return findLCAInBST(head.left, nodeA, nodeB);
        return head;
    }

    /**
     * Find the lowest common ancestor in binary tree.
     * Assuming nodeA and nodeB are always existed and head is not null.
     *
     * In the binary search tree, we can easily know where is nodeA and where is nodeB, but in binary tree, we don't know that.
     *
     * Three are 3 situations:
     *  - head equals either nodeA or nodeB, return head.
     *  - nodeA and nodeB exist in different side of head.
     *  - nodeA and nodeB exist in same side of head.
     * And there's an assumption, nodeA and nodeB are always existed.
     * So we find nodeA and nodeB in both left and right sides, say result_left and result_right.
     * if both result_left and result_right are not null, that means, one node from left and one node from right, the head is LCA.
     * Otherwise return either result_left or result_right which is not null.
     *
     * @param head
     * @param nodeA
     * @param nodeB
     * @return
     */
    public static TreeNode findLCAInBT(TreeNode head, TreeNode nodeA, TreeNode nodeB) {
        if(head == null) return null;
        if(head.val == nodeA.val || head.val == nodeB.val) return head;
        TreeNode left = findLCAInBT(head.left, nodeA, nodeB);
        TreeNode right = findLCAInBT(head.right, nodeA, nodeB);
        if(left != null && right != null) return head;
        return left != null ? left : right;
    }

    /**
     * Return the in order path of binary tree.
     *
     * @param head
     * @return
     */
    public static List<TreeNode> getInOrderPath(TreeNode head) {
        List<TreeNode> res = new ArrayList<>();
        if(head == null) return res;
        res.addAll(getInOrderPath(head.left));
        res.add(head);
        res.addAll(getInOrderPath(head.right));
        return res;
    }

    /**
     * Return the path from head to given node
     * There are 3 situations:
     *  - if head equals to node, add head into result and return.
     *  - if the node exists in left side of head, head + left_result.
     *  - if the node exists in right side of head, head + right_result.
     *  - the node may not exist.
     * @param head
     * @param node
     * @return
     */
    public static List<TreeNode> getPath(TreeNode head, TreeNode node) {
        List<TreeNode> res = new ArrayList<>();
        if(head == null) return res;
        if(head.val == node.val) {
            res.add(head);
            return res;
        }
        res.add(head);
        List<TreeNode> left = getPath(head.left, node);
        if(!left.isEmpty() && left.get(left.size() - 1).val == node.val) {
            res.addAll(left);
            return res;
        }
        List<TreeNode> right = getPath(head.right, node);
        if(!right.isEmpty() && right.get(right.size() - 1).val == node.val) {
            res.addAll(right);
        }
        return (res.get(res.size() - 1).val == node.val) ? res : new ArrayList<>();
    }

    /**
     * Solution2
     *
     * above solution is a recursive from top to bottom, and we also can implement a recursive from bottom to top.
     * @param head
     * @param node
     * @return
     */
    public static List<TreeNode> getPath2(TreeNode head, TreeNode node) {
        List<TreeNode> res = new ArrayList<>();
        helper(head, node, res);
        return res;
    }

    /**
     * Find the number of edges from head to node.
     * @param head
     * @param node
     * @return
     */
//    public static int getPathLength(TreeNode head, TreeNode node) {
//        if(head == null) return 0;
//        if(head.val == node.val) return 1;
//        int left = getPathLength(head.left, node);
//        if(left != 0) return left + 1;
//        return getPathLength(head.right, node) + 1;
//    }

    public static boolean helper(TreeNode head, TreeNode node, List<TreeNode> res) {
        if(head == null) return false;
        if(head.val == node.val || helper(head.left, node, res) || helper(head.right, node, res)) {
            res.add(head);
            return true;
        }
        return false;
    }

    /**
     * Return the path between nodeA and nodeB.
     * Assuming nodeA and nodeB are always existed.
     * Basically, this problem is similar with LCA.
     * There are 3 situations:
     *  - The head equals either nodeA or nodeB, the path is start from head.
     *  - The LCA is in either left of head or right of head.
     *  - nodeA equals nodeB.
     * @param head
     * @param nodeA
     * @param nodeB
     * @return
     */
    public static List<TreeNode> getPath(TreeNode head, TreeNode nodeA, TreeNode nodeB) {
        List<TreeNode> res = new ArrayList<>();
        return res;
    }



    public static void main(String[] args) {
        BTreePrinter.printNode(TreeNode.getBSTSample());
        System.out.println(findLCAInBST(TreeNode.getBSTSample(), new TreeNode(1), new TreeNode(3)).val);
        System.out.println(findLCAInBST(TreeNode.getBSTSample(), new TreeNode(1), new TreeNode(2)).val);
        System.out.println(findLCAInBST(TreeNode.getBSTSample(), new TreeNode(1), new TreeNode(1)).val);
        System.out.println(findLCAInBST(TreeNode.getBSTSample(), new TreeNode(1), new TreeNode(7)).val);
        System.out.println(findLCAInBST(TreeNode.getBSTSample(), new TreeNode(4), new TreeNode(4)).val);

        BTreePrinter.printNode(TreeNode.getBTSample());
        System.out.println(findLCAInBT(TreeNode.getBTSample(), new TreeNode(8), new TreeNode(3)).val);
        System.out.println(findLCAInBT(TreeNode.getBTSample(), new TreeNode(8), new TreeNode(4)).val);
        System.out.println(findLCAInBT(TreeNode.getBTSample(), new TreeNode(8), new TreeNode(5)).val);
        System.out.println(findLCAInBT(TreeNode.getBTSample(), new TreeNode(8), new TreeNode(2)).val);
        System.out.println(findLCAInBT(TreeNode.getBTSample(), new TreeNode(3), new TreeNode(7)).val);
        System.out.println(findLCAInBT(TreeNode.getBTSample(), new TreeNode(5), new TreeNode(5)).val);

        BTreePrinter.printNode(TreeNode.getBTSample());
        DisplayUtils.printList(getInOrderPath(TreeNode.getBTSample()));

        BTreePrinter.printNode(TreeNode.getBTSample());
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(3)));
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(5)));
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(2)));
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(4)));
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(9)));

        BTreePrinter.printNode(TreeNode.getBTSample());
        DisplayUtils.printList(getPath2(TreeNode.getBTSample(), new TreeNode(3)));
        DisplayUtils.printList(getPath2(TreeNode.getBTSample(), new TreeNode(5)));
        DisplayUtils.printList(getPath2(TreeNode.getBTSample(), new TreeNode(2)));
        DisplayUtils.printList(getPath2(TreeNode.getBTSample(), new TreeNode(4)));
        DisplayUtils.printList(getPath2(TreeNode.getBTSample(), new TreeNode(9)));

        BTreePrinter.printNode(TreeNode.getBTSample());
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(3), new TreeNode(7)));
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(3), new TreeNode(3)));
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(8), new TreeNode(1)));
        DisplayUtils.printList(getPath(TreeNode.getBTSample(), new TreeNode(8), new TreeNode(4)));
    }
}
