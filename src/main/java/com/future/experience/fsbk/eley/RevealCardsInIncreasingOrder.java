package com.future.experience.fsbk.eley;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/reveal-cards-in-increasing-order/
 *
 * Thoughts:
 * - The question is we know the output and need to find the input.
 *  - One solution is, imagine we have an empty input array which moves based on the given reveal way.
 *  - For each move, we fill a value based on the output array.
 * - The reveal way looks like take and insert to the back(like a queue)
 * -
 */
public class RevealCardsInIncreasingOrder {
    public int[] deckRevealedIncreasing(int[] deck) {
        if(deck == null || deck.length < 1) {
            return deck;
        }
        Arrays.sort(deck);
        Queue<Integer> queue = new LinkedList<>(); //the position queue
        int[] res = new int[deck.length];
        for(int i = 0; i < deck.length; i++) {
            queue.offer(i);
        }
        for(int i = 0; i < deck.length; i++) {
            int pos = queue.poll();
            res[pos] = deck[i];
            if(!queue.isEmpty()) {
                queue.offer(queue.poll());
            }
        }
        return res;
    }
}
