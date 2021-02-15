package com.future.experience.gugou;

/**
 * 例子：输入为两个string 比如第一个例子是xxzz和xx1azz，输出为一个formatted的output 第一个例子输出是 “” became “1a” at index 2
 * xxzz vs xx1azz, output: “” became “1a” at index 2
 * xx11vv vs xx1bbv, output: “1v” became “1b” at index 3
 * xx11vv vs xx, output: “11vv” became “” at index 2
 * 做法是两个pointer，一起移动… 面试官一开始对java substring第二个parameter是exclusive的有疑问，查了doc以后表示认可 写完跑了两个例子就结束了
 */
public class DiffBetweenString {
    /**
     * Find the common prefix and suffix, then we can get the different part.
     * @param str1
     * @param str2
     * @return
     */
    public String getDiff(String str1, String str2) {
        if(str1.equals(str2)) {
            return "";
        }
        int p1 = 0, p2 = 0;
        while (p1 < str1.length() && p2 < str2.length() && str1.charAt(p1) == str2.charAt(p2)) {
            p1++;
            p2++;
        }
        int s1 = str1.length() - 1, s2 = str2.length() - 1;
        while (s1 >= p1 && s2 >= p2 && str1.charAt(s1) == str2.charAt(s2)) {
            s1--;
            s2--;
        }
        return str1.substring(p1, s1 + 1) + " become " + str2.substring(p2, s2 + 1) + " at index " + p1;
    }

    public static void main(String[] args) {
        DiffBetweenString p = new DiffBetweenString();
        System.out.println(p.getDiff("xxzz", "xx1azz"));
        System.out.println(p.getDiff("xx11vv", "xx1bbv"));
        System.out.println(p.getDiff("xx11vv", "xx"));
        System.out.println(p.getDiff("aabb", "aabb"));
    }
}
