package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.List;

/**
 * 给一个map
 * {1，{‘A’,'B' , 'C'}},
 * {2,   { 'D' , 'E' , 'F' }}
 * 。。
 * 。。
 * {0， {'X', 'Y', 'Z'}}
 *
 * 按一次1 返回A，连续两次返回B， 连续四次重新返回A
 * 输入一组数字，不清楚之间间隔，返回所有可能的字符串数组。
 * 例如： 11 -> "AA", "B"
 *             1112 -> "AAAD", "ABD", 'BAD', 'CD'
 */
public class FindPossibleString {
    /**
     * map[0] = {' ', ' ', ' '}
     * map[1] = {'A', 'B', 'C'}
     * @param map
     * @return
     */
    public List<String> combination(char[][] map, String str) {
        List<String> res = new ArrayList<>();
        helper(str, 0, "", res, map);
        return res;
    }

    private void helper(String str, int start, String cur, List<String> res, char[][] map) {
        if(start >= str.length()) {
            res.add(cur);
            return;
        }

        int digit = str.charAt(start) - '0';
        char[] chars = map[digit];

        //press once
        helper(str, start + 1, cur + chars[0], res, map);
        //press two
        if(start < str.length() - 1 && str.charAt(start) == str.charAt(start + 1)) {
            helper(str, start + 2, cur + chars[1], res, map);
        }

        if(start < str.length() - 2 && chars.length == 3 &&str.charAt(start) == str.charAt(start + 2)) {
            helper(str, start + 3, cur + chars[2], res, map);
        }
    }

    public static void main(String[] args) {
        char[][] map = new char[][] {
                new char[]{' ', ' ', ' '},
                new char[]{'A', 'B', 'C'},
                new char[]{'D', 'E', 'F'},
                new char[]{'G', 'H', 'I'},
                new char[]{'J', 'K', 'L'},
                new char[]{'M', 'N', 'O'},
                new char[]{'P', 'Q', 'R'},
                new char[]{'S', 'T', 'U'},
                new char[]{'V', 'W', 'X'},
                new char[]{'Y', 'Z', ' '}
        };

        FindPossibleString p = new FindPossibleString();
        p.combination(map, "1112").forEach(System.out::println);
    }
}
