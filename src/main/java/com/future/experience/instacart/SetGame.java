package com.future.experience.instacart;

import java.util.*;

/**
 *
 * Given an input of cards that have suits { +, -, = }, values { A, B, C }, and different counts of values [1 - 3].
 * Find a valid hand. A valid hand consists of 3 cards. Where all the suits are different or the same, all the values are different or the same, and all counts are different or the same.
 *
 * Example 1.
 *
 * Given cards: { +AA, -AA, +AA, -C, -B, +AA, -AAA, -A, =AA }
 *
 * Valid hands are:
 * { +AA, +AA, +AA }
 *
 * [+, +, +] suit is the same
 * [A, A, A] value is the same
 * [2, 2, 2] count is the same
 *
 * { -A, -AA, -AAA }
 *
 * [-, -, -] suit is the same
 * [A, A, A] value is the same
 * [1, 2, 3] count is the different
 *
 * {-C, -B, -A }
 *
 * [-, -, -] suit is the same
 * [C, B, A] value is different
 * [1, 1, 1] count is the same
 *
 * { +AA, -AA, =AA }
 *
 * [+, -, =] suit is the different
 * [A, A, A] value is same
 * [2, 2, 2] count is the same
 *
 * Example 2.
 *
 * The following hand is also valid { -A, +BB, =CCC }
 *
 * [+, -, =] suit is different
 * [A, B, C] value is different
 * [1, 2, 3] count is different
 *
 * Task
 *
 * Write a program to return any first valid hand in the given list of cards from stdin.
 *
 *
 * https://leetcode.com/discuss/interview-question/554340/Instacart-or-Onsite-or-Create-Card-Hands
 *
 * Thoughts:
 * - The problem actually is trying to find a subset with size 3, and the card in the set meets a condition, so the first
 * solution in my mind is backtracking, the time complexity is O(n^2).
 * But in this problem, each card in the set has a relationship, let's try to take some advantage from this relationship.
 * if we already have 2 cards, we can generate third card, but it's still a subset with size 2, the time complexity still O(n^2).
 *
 * There are 81 unique card, so we can aggregate it first, likes use a hashmap with size 81, the key is card and the value is count.
 * Then we go through the entries in the map, for each entry:
 * - if the count equals to or is greater than 3, found a valid set.
 * - otherwise, choose next entry as the second card, then generate third card, check if we have third card in hashmap.
 *
 */
public class SetGame {

    /**
     * - It may contain duplicated cards.
     * - The count of unique card is limited, at most 81.
     * - If we already have two cards, we are able to generate third card.
     * @param cards
     * @return
     */
    public List<String> findValidSetV2(List<String> cards) {
        Map<String, Integer> counter = new HashMap<>();
        for(String card : cards) {
            counter.put(card, counter.getOrDefault(card, 0) + 1);
        }

        List<String> uniqueCards = new ArrayList<>(counter.keySet());
        for(int i = 0; i < uniqueCards.size(); i++) {
            if(counter.get(uniqueCards.get(i)) >= 3) {
//                return Arrays.asList(uniqueCards.get(i), uniqueCards.get(i), uniqueCards.get(i));
                System.out.println(Arrays.asList(uniqueCards.get(i), uniqueCards.get(i), uniqueCards.get(i)));
            }
            for(int j = i + 1; j < uniqueCards.size(); j++) {
                for(int k = j + 1; k < uniqueCards.size(); k++) {
                    if(isValid(uniqueCards.get(i), uniqueCards.get(j), uniqueCards.get(k))) {
//                        return Arrays.asList(uniqueCards.get(i), uniqueCards.get(j), uniqueCards.get(k));
                        System.out.println(Arrays.asList(uniqueCards.get(i), uniqueCards.get(j), uniqueCards.get(k)));
                    }
                }
            }
        }
        return new ArrayList<>();
    }


    private boolean isValid(String card1, String card2, String card3) {
        int[] first = parseToInts(card1);
        int[] second = parseToInts(card2);
        int[] third = parseToInts(card3);

        for(int i = 0; i < 3; i++) {
            if(!isValid(first[i], second[i], third[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int a, int b, int c) {
        return isAllSame(a, b, c) || isAllDiff(a, b, c);
    }

    private boolean isAllSame(int a, int b, int c) {
        return (a == b) && (a == c);
    }

    private boolean isAllDiff(int a, int b, int c) {
        return (a != b) && (a != c) && (b != c);
    }

    // int[]: {idx of suit, idx of value, number of value}
    private static final char[] SUITS = new char[]{'+', '-', '='};
    private static final char[] VALUES = new char[]{'A', 'B', 'C'};
    private int[] parseToInts(String card) {
        int[] res = new int[3];
        for(int i = 0; i < SUITS.length; i++) {
            if(card.charAt(0) == SUITS[i]) {
                res[0] = i;
                break;
            }
        }

        for(int i = 0; i < VALUES.length; i++) {
            if(card.charAt(1) == VALUES[i]) {
                res[1] = i;
                break;
            }
        }

        res[2] = card.length() - 1;
        return res;
    }





    public List<String> findValidSet(List<String> cards) {
        Map<String, Integer> counter = new HashMap<>();

        //count card
        for(String card : cards) {
            counter.put(card, counter.getOrDefault(card, 0) + 1);
        }

        List<String> uniqueCards = new ArrayList<>(counter.keySet());
        for(int i = 0; i < uniqueCards.size(); i++) {
            if(counter.get(uniqueCards.get(i)) >= 3) {
                return Arrays.asList(uniqueCards.get(i), uniqueCards.get(i), uniqueCards.get(i));
            } else {
                for(int j = i + 1; j < uniqueCards.size(); j++) {
                    String secCard = uniqueCards.get(j);
                    String thirdCard = generateThirdCard(uniqueCards.get(i), secCard);
                    if(counter.containsKey(thirdCard)) {
//                        return Arrays.asList(uniqueCards.get(i), secCard, thirdCard);
                        System.out.println(Arrays.asList(uniqueCards.get(i), secCard, thirdCard));
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    private static final Set<Character> TYPE = new HashSet<>(Arrays.asList('+', '-', '='));
    private static final Set<Character> VALUE = new HashSet<>(Arrays.asList('A', 'B', 'C'));
    private String generateThirdCard(String first, String second) {
        if(first.equals(second)) {
            return first;
        }

        String thirdCard = "";
        if(first.charAt(0) == second.charAt(0)) {
            thirdCard += first.charAt(0);
        } else {
            for(char ch : TYPE) {
                if(ch != first.charAt(0) && ch != second.charAt(0)) {
                    thirdCard += ch;
                }
            }
        }

        if(first.charAt(1) == second.charAt(1)) {
            thirdCard += first.charAt(1);
        } else {
            for(char ch : VALUE) {
                if(ch != first.charAt(1) && ch != second.charAt(1)) {
                    thirdCard += ch;
                }
            }
        }

        if(first.length() == second.length()) {
            for(int i = 2; i < first.length(); i++) {
                thirdCard += thirdCard.charAt(thirdCard.length() - 1);
            }
        } else {
            int firstSize = first.length() - 1;
            int secondSize = second.length() - 1;
            for(int i = 1; i <= 3; i++) {
                if(i != firstSize && i != secondSize) {
                    for(int j = 1; j < i; j++) {
                        thirdCard += thirdCard.charAt(thirdCard.length() - 1);
                    }
                }
            }
        }
        return thirdCard;
    }

    public static void main(String[] args) {
        SetGame g = new SetGame();
        List<String> cards = Arrays.asList("+AA", "-AA", "+AA", "-C", "-B", "+AA", "-AAA", "-A", "=AA");
//        System.out.println(g.findValidSet(cards));
        System.out.println("====================");
        System.out.println(g.findValidSetV2(cards));
    }
}
