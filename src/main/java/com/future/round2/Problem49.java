package com.future.round2;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/description/
 *
 * Given an array of strings, group anagrams together.

 For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Return:

 [
 ["ate", "eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note: All inputs will be in lower-case.

 * Created by xingfeiy on 12/6/17.
 */
public class Problem49 {

    /**
     * Analyze:
     * If we can compute a same hashcode for anagrams, we can beat below two solutions.
     * The way to sort each word, which is an alternative hash solution, use the sorted characters as hashcode,
     * but sort is a little bit expensive.
     *
     * Here, introduce a way to compute the hashcode by prime.
     *
     * If a word consist of unique characters, we can use an integer to represent it, since an integer has 32bits and there are
     * only 26 characters. But if the word contains duplicate characters, it won't work, abbb and aabb will generate same hashcode.
     *
     * Prime, A prime is a number greater than 1 that has no positive divisors other than 1 and itself.
     * So, if we use 26 primes represent 26 lower alphabets, and compute its products, the anagrams must have same products.
     * Why prime?
     * If we use non-prime, like use 3 represent a, and 9 represents b, the product is 27 that could represent ab or aaa.
     *
     * How to compute prime?
     * Problem204.
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsPrimeSolution(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs == null || strs.length < 1) return res;
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        Map<Integer, List<String>> map = new HashMap<>();
        for(String str : strs) {
            int hash = 1;
            //Attention: Overflow!!!!
            for(char ch : str.toCharArray()) {
                hash *= primes[ch - 'a'];
            }
            if(map.containsKey(hash)) {
                map.get(hash).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(hash, list);
            }
        }
        res.addAll(map.values());
        return res;
    }


    /**
     * Got this idea from leetcode, this solution is similar with the below straightforward solution, just sort each word
     * and check if it existed in hashmap, i have no idea why this solution works but the straightforward solution not.
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs == null || strs.length < 1) return res;
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newStr = new String(chars);
            if(map.containsKey(newStr)) {
                map.get(newStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(newStr, list);
            }
        }
        res.addAll(map.values());
        return res;
    }

    /**
     * Analyze:
     * To check if two words are anagrams, we can use a int[26].
     * We can just try the straightforward way, it works, but got TLE
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsTLE(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs == null || strs.length < 1) return res;

        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            boolean found = false;
            for(Map.Entry<String, List<String>> entry : map.entrySet()) {
                if(isAnagrams(entry.getKey(), str)) {
                    found = true;
                    entry.getValue().add(str);
                    break;
                }
            }
            List<String> list = new ArrayList<>();
            list.add(str);
            if(!found) map.put(str, list);
        }
        res.addAll(map.values());
        return res;

    }

    private boolean isAnagrams(String str1, String str2) {
        if(str1.equals(str2)) return true;
        if(str1.length() != str2.length()) return false;
        int[] chars = new int[26];
        Arrays.fill(chars, 0);
        for(char ch : str1.toCharArray()) {
            chars[ch - 'a']++;
        }
        for(char ch : str2.toCharArray()) {
            chars[ch - 'a']--;
            if(chars[ch - 'a'] < 0) return false;
        }
        return true;
    }
}
