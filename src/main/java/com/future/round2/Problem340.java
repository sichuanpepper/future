package com.future.round2;

/**
 Given a string you need to print longest possible substring that has exactly M unique characters.
 If there are more than one substring of longest possible length, then print any one of them.

 Examples:

 "aabbcc", k = 1
 Max substring can be any one from {"aa" , "bb" , "cc"}.

 "aabbcc", k = 2
 Max substring can be any one from {"aabb" , "bbcc"}.

 "aabbcc", k = 3
 There are substrings with exactly 3 unique characters
 {"aabbcc" , "abbcc" , "aabbc" , "abbc" }
 Max is "aabbcc" with length 6.

 "aaabbb", k = 3
 There are only two unique characters, thus show error message.

 * Created by xingfeiy on 4/3/18.
 */
public class Problem340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || k < 1) return 0;
        int[] map = new int[256];
        int p1 = 0, p2 = 0, count = 0, res = 0;
        while (p2 < s.length() && p1 <= p2) {
            // map(p2) is 0, means new unique character, count++, and meantime, map(p2) + 1
            if(map[s.charAt(p2)]++ == 0) count++;
            if(count == k) { //find k unique characters.
                res = Math.max(res, p2 - p1 + 1);
                p2++; //maybe longer is next is same as current p2.
            } else if (count > k) { //found more than k unique characters, move p1.
                // map(p1) - 1, and if map(p1) = 0, that means a unique characters are totally took away.
                if(--map[s.charAt(p1++)] == 0) count--;
            } else {
                p2++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Problem340 p = new Problem340();
        System.out.println(p.lengthOfLongestSubstringKDistinct("", 2));
        System.out.println(p.lengthOfLongestSubstringKDistinct("abc", 0));
        System.out.println(p.lengthOfLongestSubstringKDistinct("aabbcc", 1));
        System.out.println(p.lengthOfLongestSubstringKDistinct("aabbcc", 2));
        System.out.println(p.lengthOfLongestSubstringKDistinct("aabbcc", 3));
        System.out.println(p.lengthOfLongestSubstringKDistinct("aabbcc", 4));
    }
}
