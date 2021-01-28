package com.future.round2;

/**
 * https://leetcode.com/problems/reorganize-string/
 */
public class Problem767 {
    public String reorganizeString(String S) {
        if(S == null || S.length() < 1) return "";
        int[] map = new int[26];
        char maxCh = ' ';
        int maxCnt = 0;
        for(char ch : S.toCharArray()) {
            map[ch - 'a']++;
            if(map[ch - 'a'] > maxCnt) {
                maxCnt = map[ch - 'a'];
                maxCh = ch;
            }
        }

        if(maxCnt > (S.length() + 1) / 2) return "";
        int index = 0;
        char[] res = new char[S.length()];
        while(map[maxCh - 'a'] > 0) {
            res[index] = maxCh;
            index += 2;
            map[maxCh - 'a']--;
        }

        for(int i = 0; i < map.length; i++) {
            while(map[i]-- > 0) {
                if(index >= S.length()) {
                    index = 1;
                }
                res[index] = (char)('a' + i);
                index += 2;
            }
        }
        return new String(res);
    }

    public static void main(String[] args) {
        Problem767 p = new Problem767();
        System.out.println(p.reorganizeString("zqugrfbsznyiwbokwkpvpmeyvaosdkedbgjogzdpwawwl"));
    }
}
