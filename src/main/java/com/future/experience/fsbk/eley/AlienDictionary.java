package com.future.experience.fsbk.eley;

/**
 * https://www.lintcode.com/problem/alien-dictionary/
 *
 * Thoughts:
 * - It's easy to see it's a graph structure.
 * - If a valid order is available, there must be at least one node with 0 indegree.
 * - We traversal graph from 0 indegree nodes, once we visited a 0 indegree node:
 *      - Update the indegree of its adjacent nodes, put all adjacent nodes with 0 indegree into to queue for next visit.
 *
 * Notices:
 * - We may have more than one 0 indegree nodes, that means there are multiple results.
 * - It's possible we have cycle in the graph, that means there is no result.
 */
public class AlienDictionary {
}
