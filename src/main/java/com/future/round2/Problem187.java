package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/description/
 *
 All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

 Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 For example,

 Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

 Return:
 ["AAAAACCCCC", "CCCCCAAAAA"].
 * Created by xingfeiy on 1/5/18.
 */
public class Problem187 {
    /**
     * Analyze:
     * Two pointers: p1, p2(p1 + 10), move it together
     *  - if p1 == p2, move to next
     *  - else p1 move to next, and p2 initial as p1 + 10
     *  Time complexity: O(n), Space complexity: O(1)
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if(s == null) return new ArrayList<>();
        Set<String> existed = new HashSet<>();
        Set<String> existedMany = new HashSet<>();
        for(int i = 0; i <= s.length() - 10; i++) {
            String token = s.substring(i, i + 10);
            if(!existed.add(token)) {  //for the add method, if the added value is already existed return false, otherwise return true.
                existedMany.add(token);
            }
        }
        return new ArrayList<>(existedMany);
    }
}
