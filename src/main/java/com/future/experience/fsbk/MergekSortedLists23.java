package com.future.experience.fsbk;

import com.future.utils.ListNode;

import java.util.PriorityQueue;

public class MergekSortedLists23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length < 1) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b)->(Integer.compare(a.val, b.val)));
        for(ListNode node : lists) pq.offer(node);
        return null;
    }
}
