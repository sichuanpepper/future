package com.future.experience.gugou;

import java.util.*;

public class FindCommonElements {
    /**
     * Given a list of sorted list, find the common elements.
     *
     * Pay attention: the list may contain duplicated number!
     *
     * Questions need to clarify:
     * - [1, 1, 2, 2, 2, 3]
     * - [1, 1, 2, 2, 3]
     *
     * What the results we are expected? [1, 2] or [1, 1, 2, 2]?
     *
     * @param lists
     * @return
     */
    public List<Integer> findCommonElements(List<List<Integer>> lists) {
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.get(0), b.get(0)));
        for(List<Integer> list : lists) {
            queue.offer(list);
        }
        List<Integer> res = new ArrayList<>();
        while (queue.size() == lists.size()) {
            int pre = queue.peek().get(0);
            int i = 0;
            for(; i < lists.size(); i++) {
                if(queue.peek().get(0) != pre) break;
                List<Integer> list= queue.poll();
                while (list.size() > 0 && list.get(0) == pre) {  // may have duplicated elements
                    list.remove(0);
                }
                if(list.size() > 0) queue.offer(list);
            }
            if(i == lists.size()) {
                res.add(pre);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5})));
        lists.add(new ArrayList<>(Arrays.asList(new Integer[]{1, 3, 4, 5})));
        lists.add(new ArrayList<>(Arrays.asList(new Integer[]{3, 4, 5})));
        lists.add(new ArrayList<>(Arrays.asList(new Integer[]{2, 3, 4})));
        lists.add(new ArrayList<>(Arrays.asList(new Integer[]{1, 1, 1, 2, 3, 3, 3, 4, 5, 6, 7, 8, 9})));
        new FindCommonElements().findCommonElements(lists).stream().forEach(System.out::println);
    }
}
