package com.future.experience.instacart;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=742300&highlight=instacart
 */
public class Password {
    public static char parse(String file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
            String first = lines.get(0).trim();
            int[] idx = parseIndex(first);
            return findChar(lines, idx[1], idx[0], lines.size() - 1);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ' ';
    }

    /**
     * [0, 0]
     * @param line
     * @return index of char, index of line
     */
    private static int[] parseIndex(String line) {
        int start = line.indexOf('['), end = line.indexOf(']');
        line = line.substring(start + 1, end);
        String[] tokens = line.split(",");
        int[] res = new int[tokens.length];
        for(int i = 0; i < tokens.length; i++) {
            res[i] = Integer.parseInt(tokens[i].trim());
        }
        return res;
    }

    private static char findChar(List<String> lines, int idxOfLine, int idxOfChar, int start) {
        return lines.get(start - idxOfLine).charAt(idxOfChar);
    }

    /**
     * 第二小问：同样文件， 需要解析多个这样的字符，然后根据index组成一个password返回
     *
     * 文件内容：
     * 1
     * [2, 3]
     * ABCDEFG
     * HIGKLMN
     * OPQRSTU
     * VWXYZAB
     *  
     * 0
     * [0, 0]
     * ABCDEFG
     * HIGKLMN
     * OPQRSTU
     * VWXYZAB
     *
     * 第一个字符为C, index 为 1， 第二个字符为V, index 为0。所以返回“VC”
     * @param file
     * @return
     */
    public static String parseV2(String file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
            List<int[]> idxOfSeg = new ArrayList<>();  //int[]: idx of password, start pos, end pos
            int p = 0;
            while(p < lines.size()) {
                String line = lines.get(p);
                if(Character.isDigit(line.charAt(0))) {
                    int[] tmp = new int[3];
                    tmp[0] = Integer.parseInt(line);
                    tmp[1] = p + 1;
                    int p1 = p + 1;
                    while(p1 < lines.size() && isNotBlank(lines.get(p1))) {
                        p1++;
                    }
                    tmp[2] = p1 - 1;
                    idxOfSeg.add(tmp);
                    p = p1;
                } else {
                    p++;
                }
            }

            //generate string
            char[] res = new char[idxOfSeg.size()];
            for(int[] pair : idxOfSeg) {
                int[] idx = parseIndex(lines.get(pair[1]));
                res[pair[0]] = findChar(lines, idx[1], idx[0], pair[2]);
            }
            return new String(res);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private static boolean isNotBlank(String str) {
        return str != null && str.length() > 0 && (Character.isDigit(str.charAt(0)) || Character.isLetter(str.charAt(0)) || str.charAt(0) == '[');
    }

    public static void main(String[] args) {
        System.out.println(parse("/Users/xingfeiyu/tmp/password.txt"));
        System.out.println(parseV2("/Users/xingfeiyu/tmp/password2.txt"));
        System.out.println(parseV2("/Users/xingfeiyu/tmp/password3.txt"));
    }
}
