package com.future.oa.amazon;

import com.future.utils.DisplayUtils;

import java.util.*;

/**
 * Created by xingfeiy on 3/11/18.
 */
public class SortLogs {
    public List<String> problem(List<String> logs) {
        if(logs == null || logs.size() < 1) return logs;
        Collections.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.equals(o2)) return 0;
                String key1 = o1.substring(o1.indexOf(" ") + 1, o1.length());
                String key2 = o2.substring(o1.indexOf(" ") + 1, o2.length());
                if(key1.equals(key2)) return 0;
                int p1 = 0, p2 = 0;
                while (p1 < key1.length() || p2 < key2.length()) {
                    if(p1 >= key1.length()) return 1;
                    if(p2 >= key2.length()) return -1;
                    if(key1.charAt(p1) == key2.charAt(p2)) {

                        p1++;
                        p2++;
                        continue;
                    } else if(Character.isDigit(key1.charAt(p1)) && Character.isDigit(key2.charAt(p2)) ||
                            Character.isAlphabetic(key1.charAt(p1)) && Character.isAlphabetic(key2.charAt(p2))) {
                        return Character.compare(key1.charAt(p1), key2.charAt(p2));
                    } else {
                        return Character.isAlphabetic(key1.charAt(p1)) ? 1 : 0;
                    }
                }
                return 0;
            }
        });
        return logs;
    }

    public static void main(String[] args) {
        System.out.println("abc".substring(3, 3));
        SortLogs problem = new SortLogs();
        List<String> logs = Arrays.asList(new String[]{"abc ","abc 183", "bcd 134 ", "cde 134 yiu", "def abc 183 "});
        DisplayUtils.printList(problem.problem(logs));
    }


}
