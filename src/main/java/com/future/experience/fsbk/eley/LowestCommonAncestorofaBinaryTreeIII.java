package com.future.experience.fsbk.eley;

/**
 * https://leetcode.ca/all/1650.html
 *
 * Thoughts:
 * In this problem, each node has its parent pointer so we are able to traversal up.
 *
 * Solution 1:
 * Start from first node node1 and traversal up till the root, we use a set to store the nodes on the path.
 * Then start from second node node2 and traversal up, for each move, we check if we have the current node in the set.
 * Return the first one.
 *
 * Solution 2:
 * We know a fact that if node1 and node2 located in same level, then the first place where they meet is the LCA.
 * So we can traversal up from both node1 and node2 and get the paths, move diff steps for the deeper node and make sure they
 * can move from same level.
 *
 * Solution 3:
 * If the distance from node1 to root is dis1, and distance from node2 to root is dis2.
 * It's easy to see (dis1 + dis2) = (dis2 + dis1), which means node1 traversal up from original place to root, and then traversal from node2 to root again.
 * Same thing for node2.
 *
 * Then the first place is the LCA.
 *
 */
public class LowestCommonAncestorofaBinaryTreeIII {
}
