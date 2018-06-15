package com.future.experience.aibiying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *

 Implement an iterator to flatten a 2d vector.
 For example,
 Given 2d vector =
 [
 [1,2],
 [3],
 [4,5,6]
 ]
 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

 * Created by xingfeiy on 6/5/18.
 */
public class Flatten2DVector {
    private List<List<Integer>> vec2d;
    private Iterator<List<Integer>> rowIter;
    private Iterator<Integer> colIter;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        this.vec2d = vec2d == null ? new ArrayList<>() : vec2d;
        rowIter = this.vec2d.iterator();
        colIter = Collections.emptyIterator();
    }

    public int next() {
        return colIter.next();
    }

    public boolean hasNext() {
        if(colIter.hasNext()) return true;
        if(!rowIter.hasNext()) return false;
        colIter = rowIter.next().iterator();
        while (!colIter.hasNext() && rowIter.hasNext()) {
            colIter = rowIter.next().iterator();
        }
        return colIter.hasNext();
    }
}
