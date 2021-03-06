package com.future.experience.aibiying;

import java.util.*;

/**
 *
 We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.

 For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A`
 and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.

 We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed.
 Each allowed triple is represented as a string of length 3.

 Return true if we can build the pyramid all the way to the top, otherwise false.

 Example 1:
 Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
 Output: true
 Explanation:
 We can stack the pyramid like this:
     A
    / \
   D   E
  / \ / \
 X   Y   Z

 This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
 Example 2:
 Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
 Output: false
 Explanation:
 We can't stack the pyramid to the top.
 Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.

 Note:
 bottom will be a string with length in range [2, 8].
 allowed will have length in range [0, 200].
 Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.

 * Created by xingfeiy on 6/13/18.
 */
public class PyramidsTransitionMatrix {

    /**
     * Analyze:
     * - If there's only one character, that means it's top already, our job is done.
     * - From bottom to up, each row generates multiple upper shorter rows.
     * - the allowed are triples, for each check, we have to check first 2 characters.
     *
     * @param bottom
     * @param allowed
     * @return
     */
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> map = new HashMap<>();
        for(String a : allowed) {
            Set<Character> val = map.getOrDefault(a.substring(0, 2), new HashSet<>());
            val.add(a.charAt(2));
            map.put(a.substring(0, 2), val);
        }
        return helper(bottom, map);
    }

    private boolean helper(String bottom, Map<String, Set<Character>> map) {
        if(bottom.length() < 2) return true;
        List<String> upperRow = new ArrayList<>();
        getUpperRows(bottom, 0, "", map, upperRow);
        for(String str : upperRow) {
            if(helper(str, map)) return true;
        }
        return false;
    }

    private void getUpperRows(String bottom, int start, String curStr, Map<String, Set<Character>> map, List<String> res) {
        if(start == bottom.length() - 1) {
            res.add(curStr);
            return;
        }

        String prefix = bottom.substring(start, start + 2);
        if(!map.containsKey(prefix)) return;
        for(char ch : map.get(prefix)) {
            getUpperRows(bottom, start + 1, curStr + ch, map, res);
        }
    }

//    public boolean pyramidTransition2(String bottom, List<String> allowed) {
//        Map<String, List<String>> map = new HashMap<>();
//        for (String s : allowed) {
//            String key = s.substring(0,2);
//            if (!map.containsKey(key)) map.put(key, new ArrayList<String>());
//            map.get(key).add(s.substring(2));
//        }
//
//        return helper(bottom, map);
//    }
//
//    private boolean helper(String bottom, Map<String, List<String>> map) {
//        if(bottom.length() == 1) return true;
//        for (int i = 0; i<bottom.length()-1; i++) {
//            if (!map.containsKey(bottom.substring(i,i+2))) return false;
//        }
//        List<String> ls = new ArrayList<>();
//        getList(bottom, 0, new StringBuilder(), ls, map);
//        for (String s : ls) {
//            if (helper(s, map)) return true;
//        }
//        return false;
//    }
//
//    private void getList(String bottom, int idx, StringBuilder sb, List<String> ls, Map<String, List<String>> map) {
//        if (idx == bottom.length() - 1) {
//            ls.add(sb.toString());
//            return;
//        }
//        for (String s : map.get(bottom.substring(idx, idx+2))) {
//            sb.append(s);
//            getList(bottom, idx + 1, sb, ls, map);
//            sb.deleteCharAt(sb.length()-1);
//        }
//    }

    public static void main(String[] args) {

//        "AABA"
//        ["AAA","AAB","ABA","ABB","BAC"]

        PyramidsTransitionMatrix p = new PyramidsTransitionMatrix();
        System.out.println(p.pyramidTransition("BDAFFFDB", Arrays.asList(new String[]{"EED","BGG","AGF","AGD","AGA","CCE","DCG","DCD","DCB","DCA","FGD","FGE","FGG","FGA","FGC","BFB","BFG","BFD","FAB","GAF","EDD","DBC","EDE","DBE","DBD","FAG","FFG","FFF","FFE","FFD","FFC","FFA","FDA","GCA","GBD","FDB","GBB","BEB","BEF","BEG","BED","AEA","GCB","AED","AEG","AEF","DEA","EEA","DEE","DEF","EEB","CEG","CEC","GEC","GEB","GEG","GED","BDE","BDG","BDF","GCE","AFE","AFG","AFA","AFB","EFA","DDA","EFD","DDF","EFF","EFG","CBB","CBF","CBD","BDA","ACC","ACB","ACA","ACE","BCE","BCF","BCG","DGF","ECG","DGC","ECE","ECC","DGD","CGA","CGC","CGE","FCB","FCD","DDE","FCF","GGB","FED","FEB","BBC","BBA","ADF","ADE","ADB","ADA","DFD","DFA","DFC","CDD","CDE","CDB","CDC","FBC","GDB","GDC","FBG","GDE","AAE","AAD","AAG","AAA","AAC","BAF","BAG","BAB","BAA","CAC","CAB","CAE","CAD","DAF","DAB","DAC","EAC","EAF","GAG","FAC","FAF","GAB","ABB","ABD","ABE","ABF","ABG","GCC","EBD","EBE","EBF","CFF","CFE","CFB","GFA","GFF","GFG"})));
    }





}
