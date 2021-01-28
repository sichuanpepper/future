package com.future.experience.gugou;

import java.util.*;

/**
 * Given a set of inputs which represents [from, to, comment] in google docs.
 * Transform the input with overlapping offsets & unique comments to non overlapping offsets and duplicate comments.
 *
 * Example 1:
 *
 * Input:
 * (0, 3): A
 * (2, 4): B
 * (5, 6): C
 *
 * Output:
 * (0, 2): [A]
 * (2, 3): [A, B]
 * (3, 4): [B]
 * (5, 6): [C]
 * Example 2:
 *
 * Input:
 * (0, 3): A
 * (0, 3): B
 * (2, 4): C
 * (5, 6): D
 *
 * Output:
 * (0, 2): [A, B]
 * (2, 3): [A, B, C]
 * (3, 4): [C]
 * (5, 6): [D]
 */

public class MergeIntervalsWithLabels {
    private class Node {
        public int time;
        public boolean isStart;
        public String comment;
    }

    public List<String> transform(List<String> strList) {
        return null;
    }

    private Node parseToNode(String str) {
        return null;
    }
}
