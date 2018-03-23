package com.future.round2;

import com.future.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-bst/description/
 *
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
 in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization
 algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be
 deserialized to the original tree structure.

 The encoded string should be as compact as possible.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 * Created by xingfeiy on 3/22/18.
 */
public class Problem449 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "$";
        String str = Integer.toString(root.val) + "/";
        str += serialize(root.left) + "/";
        return str + serialize(root.right) + "/";
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() < 1) return null;
        Queue<String> values = new LinkedList<>();
        for(String val : data.split("/")) {
            if(val != null && val.length() > 0) values.offer(val);
        }
        return helper(values);
    }

    private TreeNode helper(Queue<String> values) {
        if(values.isEmpty()) return null;
        String str = values.poll();
        if(str.equals("$")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = helper(values);
        root.right = helper(values);
        return root;
    }

    public static void main(String[] args) {
        String str = "/$/1/2/";
        for(String val : str.split("/")) {
            System.out.println(val);
        }
    }
}
