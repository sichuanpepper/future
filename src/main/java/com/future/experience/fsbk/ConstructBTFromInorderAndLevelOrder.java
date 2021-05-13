package com.future.experience.fsbk;

import com.future.utils.BTreePrinter;
import com.future.utils.TreeNode;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/
 *
 * Usually, if we want to construct a tree, we need to find root node, and sub left nodes and sub right nodes,
 * then  build left sub tree and right sub tree recursively.
 *
 * In the example, it's easy to see the first number in the level order is the root, and also we can easy to find the
 * all left sub nodes and right sub nodes, if we are able to find the corresponding sub-sequence of sub nodes in level order,
 * then we can build sub left tree and sub right tree recursively.
 *
 * But the problem is the sub-sequence in level is not consecutive, we have to extract it.
 */
public class ConstructBTFromInorderAndLevelOrder {

    public TreeNode construct(int[] in, int[] level) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < level.length; i++) {
            indexMap.put(level[i], i);
        }
        return helper(in, 0, in.length - 1, level, indexMap);
    }

    /**
     * Since the sub-sequence in in-order is consecutive, we don't have to create new, just need to know the start and end.
     * @param in
     * @param start
     * @param end
     * @param level
     * @param indexMap key: node value, value: the position in level order
     * @return
     */
    private TreeNode helper(int[] in, int start, int end, int[] level, Map<Integer, Integer> indexMap) {
        if(start > end) {
            return null;
        }

        if(start == end) {
            return new TreeNode(in[start]);
        }

        TreeNode root = new TreeNode(level[0]);
        int idxInInorder = -1;
        for(int i = 0; i < in.length; i++) {
            if(in[i] == level[0]) {
                idxInInorder = i;
                break;
            }
        }
        root.left = helper(in, start, idxInInorder - 1, buildSubSeq(in, start, idxInInorder - 1, indexMap), indexMap);
        root.right = helper(in, idxInInorder + 1, end, buildSubSeq(in, idxInInorder + 1, end, indexMap), indexMap);
        return root;
    }

    private int[] buildSubSeq(int[] in, int start, int end, Map<Integer, Integer> indexMap) {
        if(start > end) {
            return new int[]{};
        }
        int[] res = new int[end - start + 1];
        List<int[]> entries = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            entries.add(new int[]{in[i], indexMap.get(in[i])});
        }
        Collections.sort(entries, (a, b) -> Integer.compare(a[1], b[1]));
        for(int i = 0; i < entries.size(); i++) {
            res[i] = entries.get(i)[0];
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode res = new ConstructBTFromInorderAndLevelOrder().construct(new int[]{4, 8, 10, 12, 14, 20, 22}, new int[]{20, 8, 22, 4, 12, 10, 14});
        BTreePrinter.printNode(res);
    }
}
