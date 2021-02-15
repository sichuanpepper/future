package com.future.experience.gugou;

import com.future.utils.DisplayUtils;

import java.util.*;

/**
 * 要求pre compute o（n），follow up二维color矩阵，要求pre compute o（mn），之后query o（k），k为query次数
 *
 *
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 *
 * You are also given some queries. Each query consists of two integers i and c, return the shortest distance between
 * the given index i and the target color c. If there is no solution return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
 * Output: [3,0,3]
 * Explanation:
 * The nearest 3 from index 1 is at index 4 (3 steps away).
 * The nearest 2 from index 2 is at index 2 itself (0 steps away).
 * The nearest 1 from index 6 is at index 3 (3 steps away).
 * Example 2:
 *
 * Input: colors = [1,2], queries = [[0,3]]
 * Output: [-1]
 * Explanation: There is no 3 in the array.
 *
 *
 * Constraints:
 *
 * 1 <= colors.length <= 5*10^4
 * 1 <= colors[i] <= 3
 * 1 <= queries.length <= 5*10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] < colors.length
 * 1 <= queries[i][1] <= 3
 */
public class ShortestDistancetoTargetColor {
    /**
     * Use a map where key is the color, and the value is the corresponding position which is sorted array.
     * For each query, get the position list by color, and then do the binary search
     * Time complexity: O(n), Space complexity: O(n)
     *
     * Instead use sorted list for the value in the map, we can also use TreeSet()
     * Both binary search and TreeSet (celling or floor) offers time complexity O(log(n))
     *
     * Binary search returns the insert position, it might be the first position of the element which is greater than insert value.
     * But we still need to know the previous one if it exists.
     * So use TreeSet will make it easily.
     *
     * Takeaways:
     * - In TreeSet, the time complexity of celling and floor are O(log(n)), returns null if there's no such element.
     * - Binary search, if the given value can't be found, the res = -insert_pos - 1, insert pos is the position of first element greater than given value.
     * - Binary search, the result may equal to the size of list.
     *
     * @param colors
     * @param queries
     * @return
     */
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        for(int i = 0; i < colors.length; i++) {
            TreeSet<Integer> pos = map.getOrDefault(colors[i], new TreeSet<>());
            pos.add(i);
            map.put(colors[i], pos);
        }

        List<Integer> res = new ArrayList<>(queries.length);
        for(int[] query : queries) {
            if(!map.containsKey(query[1])) {
                res.add(-1);
                continue;
            }
            //query[0]: index, query[1]: color
            TreeSet<Integer> posList = map.get(query[1]);
            //Returns the least element in this set greater than or equal to the given element
            Integer celling = posList.ceiling(query[0]); //return null if there's no such element.
            Integer floor = posList.floor(query[0]);
            if(celling == null && floor ==  null) {
                res.add(-1);
            } else {
                int leftDis = (celling == null) ? Integer.MAX_VALUE :  celling - query[0];
                int rightDis = (floor == null) ? Integer.MAX_VALUE : query[0] - floor;
                res.add(Math.min(leftDis, rightDis));
            }

        }
        return res;
    }

    public List<Integer> shortestDistanceColorBT(int[] colors, int[][] queries) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < colors.length; i++) {
            List<Integer> pos = map.getOrDefault(colors[i], new ArrayList<>());
            pos.add(i);
            map.put(colors[i], pos);
        }

        List<Integer> res = new ArrayList<>(queries.length);
        for(int[] query : queries) {
            if(!map.containsKey(query[1])) {
                res.add(-1);
                continue;
            }
            List<Integer> posList = map.get(query[1]);
            int insertPos = Collections.binarySearch(posList, query[0]);
            if(insertPos >= 0) {
                res.add(0);
            } else {
                insertPos = -insertPos - 1;
                if(insertPos == 0) {
                    res.add(posList.get(insertPos) - query[0]);
                } else if(insertPos == posList.size()) {
                    res.add(query[0] - posList.get(posList.size() - 1));
                } else {
                    int minStep = Math.min(query[0] - posList.get(insertPos - 1), posList.get(insertPos) - query[0]);
                    res.add(minStep);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
        //Output: [3,0,3]
        int[] colors = new int[]{1,1,2,1,3,2,2,3,3};
        int[][] queries = new int[][] {
                new int[]{1, 3},
                new int[]{2, 2},
                new int[]{6, 1},
                new int[]{3, 5}
        };
        ShortestDistancetoTargetColor p = new ShortestDistancetoTargetColor();
        DisplayUtils.printList(p.shortestDistanceColor(colors, queries));
        DisplayUtils.printList(p.shortestDistanceColorBT(colors, queries));
    }

}
