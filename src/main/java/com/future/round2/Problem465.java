package com.future.round2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 * Note:
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 * Example 1:
 * Input:
 * [[0,1,10], [2,0,5]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 *
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 *
 * Output:
 * 1
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 *
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 */
public class Problem465 {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] i : transactions) {
            map.put(i[0], map.getOrDefault(i[0], 0) + i[2]);
            map.put(i[1], map.getOrDefault(i[1], 0) - i[2]);
        }

        List<Integer> debts = new ArrayList<>(map.values());
        return dfs(debts, 0); // 为什么选0？
    }

    private int dfs(List<Integer> debts, int start) {
        while (start < debts.size() && debts.get(start) == 0) {
            start++; // 遇到0后继续往下
        }

        if (start == debts.size()) return 0;
        int rt = Integer.MAX_VALUE;

        for (int i = start + 1; i < debts.size(); i++) {
            if (debts.get(start) * debts.get(i) < 0) {
                debts.set(i, debts.get(i) + debts.get(start));
                rt = Math.min(rt, 1 + dfs(debts, start + 1));
                debts.set(i, debts.get(i) - debts.get(start));
            }
        }

        return rt;
    }

    public static void main(String[] args) {
        int[][] transactions = new int[][]{new int[]{1,0,10}, new int[]{2,1,5}};
        System.out.println(new Problem465().minTransfers(transactions));
    }
}
