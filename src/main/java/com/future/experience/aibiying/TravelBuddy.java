package com.future.experience.aibiying;

import java.util.*;

/**
 * Created by xingfeiy on 6/12/18.
 */
public class TravelBuddy {
    /**
     * example:
     * mine = [1, 2, 3, 4] means I want to travel to city 1, 2, 3
     * others = [
     *  [1, 3, 4, 6],   => buddy 1 like to travel to city 1, 3, 4, 6
     *  [1, 2]    => buddy2's list, the similarity is 50%, buddy2 is my travel buddy
     *  [1, 5, 6, 8]  => buddy3's list, the similarity is less than 50%, buddy3 is not my travel buddy
     * ]
     * @param others
     * @param mine
     * @return
     */
    public List<Similarity> sortTravelBuddy(Map<Integer, Set<Integer>> others, Set<Integer> mine) {
        List<Similarity> myBuddy = new ArrayList<>();
        for(Map.Entry<Integer, Set<Integer>> entry : others.entrySet()) {
            float s = similarity(mine, entry.getValue());
            if(Float.compare(s, 0.5f) >= 0) myBuddy.add(new Similarity(entry.getKey(), s));
        }

        Collections.sort(myBuddy, (Similarity o1, Similarity o2) -> (Float.compare(o2.s, o1.s)));
        return myBuddy;
    }

    private class Similarity {
        public int buddy;
        public float s;

        public Similarity(int buddy, float s) {
            this.buddy = buddy;
            this.s = s;
        }
    }

    private float similarity(Set<Integer> mine, Set<Integer> others) {
        Set<Integer> mineCopy = new HashSet<>(mine);
        mineCopy.retainAll(others);
        return (float)mineCopy.size() / mine.size();
    }
}
