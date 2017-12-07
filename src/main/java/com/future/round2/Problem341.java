package com.future.round2;

import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/description/
 *
 * Created by someone on 11/21/17.
 */
public class Problem341 implements Iterator<Integer> {
    public Problem341(List<Problem341> nestedList) {

    }

    @Override
    public Integer next() {
        return 0;
    }

    @Override
    public boolean hasNext() {
        return true;
    }
}
