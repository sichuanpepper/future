package com.future.round2;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
 * The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

 Now you want to find out who the celebrity is or verify that there is not one.
 The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
 You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

 You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n),
 your function should minimize the number of calls to knows.

 Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party.
 If there is no celebrity, return -1.

 * Created by someone on 11/15/17.
 */
public class Problem277 {
    /**
     * Analyze:
     * There may exist only one celebrity, or the celebrity doesn't exist.
     *
     * example: 0, 1, 2, 3, 4, 5, and 3 is celebrity.
     *  - For any non-celebrity, he/she must know at least one person if the celebrity existed.
     *  - If the person know any other people, that means this person won't be celebrity.
     *  - So we can check the possibility of celebrity for each person, we can start it from first one.
     *  - if person A knows person B, we know person A won't be celebrity, but person B might be celebrity.
     *  so, for the example above, either person 0, ,1 ,2 may know other people, or doesn't know anybody else except 3,
     *  the possible candidate always comes to 3, since all people know 3.
     *
     *  And since celebrity doesn't know anybody else, so the candidate will keep points to 3.
     *
     *  Now, we know the possible candidate 3 doesn't know the all person after itself, but we don't know if there's
     *  anybody in front of 3 and 3 knows it, so we need to one more loop to check it.
     *
     * @param n
     * @return
     */
    public int findCelebrity(int n) {
        if(n < 2) return -1;
        int candidate = 0;
        for(int i = 1; i < n; i++) {
            if(knows(candidate, i)) candidate = i;
        }

        for(int i = 0; i < n; i++) {
            if(i != candidate && (!knows(i, candidate) || knows(candidate, i))) return -1;
        }
        return candidate;
    }

    private boolean knows(int a, int b) {
        //fake code.
        return true;
    }
}
