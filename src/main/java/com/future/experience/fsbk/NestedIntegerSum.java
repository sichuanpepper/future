package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerSum {
    private class NestedInteger {
        public int val = 0;
        public List<NestedInteger> sub = new ArrayList<>();
        public boolean isSignleVal = false;
    }

    public int sum(List<NestedInteger> ni) {
        if(ni == null) return 0;
        return helper(ni, 1);
    }

    private int helper(List<NestedInteger> nis, int depth) {
        int sum = 0;
        for(NestedInteger ni : nis) {
            sum += ni.isSignleVal ? ni.val * depth : helper(ni.sub, depth + 1);
        }
        return sum;
    }
}
