package com.future.experience.instacart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Password2 {
    public static char parse(String file) {
        List<String> lines = getAllLinesV2(file);
        //assume the index line is always the first line.
        int[] index = parseIndex(lines.get(0));

        return findChar(lines, index[1], index[0], lines.size() - 1);
    }

    /**
     * Find all segments, and parse each segment
     * @param file
     * @return
     */
    public static String parseV2(String file) {
        List<String> lines = getAllLines(file);
        List<int[]> segments = findSegments(lines);
        char[] chars = new char[segments.size()];
        for(int[] seg : segments) {
            chars[seg[0]] = findChar(lines, seg);
        }
        return new String(chars);
    }

    /**
     * 3. followup：和2一样文本路径和文本，但是有多个password，要求返回第一个password。具体怎么判断当一个password结束，是根据当前字符所在的index有没有重复。比如：
     *
     * 1
     * [2,4]
     * S3KDA4
     * 4ASDSD
     * ACEEDS
     * ASDEED
     * RTRYYU
     *
     * 0
     * [0,0]
     * I3KDA4
     * XTRYYU
     *
     * 1          #《---index 1已经出现过，所以从这儿开始是第二个password，可以返回第一个password为：XK
     * [0,0]
     * L3BDA4
     * @param file
     * @return
     */
    public static String parseV3(String file) {
        List<String> lines = getAllLines(file);
        List<int[]> segments = findSegmentsV3(lines);
        char[] chars = new char[segments.size()];
        for(int[] seg : segments) {
            chars[seg[0]] = findChar(lines, seg);
        }
        return new String(chars);
    }

    private static char findChar(List<String> lines, int[] seg) {
        int[] idx = parseIndex(lines.get(seg[1]));
        return findChar(lines, idx[1], idx[0], seg[2]);
    }

    private static List<int[]> findSegments(List<String> lines) {
        int idx = 0;
        List<int[]> res = new ArrayList<>();
        while (idx < lines.size()) {
            if(isNumberLine(lines.get(idx))) {
                //a segment started
                int tmp =idx + 1;
                while (tmp < lines.size() && !isBlankLine(lines.get(tmp))) {
                    tmp++;
                }
                int[] seg = new int[3];
                seg[0] = Integer.parseInt(lines.get(idx));
                seg[1] = idx + 1;
                seg[2] = tmp - 1;
                res.add(seg);
                idx = tmp;
            } else {
                idx++;
            }
        }
        return res;
    }

    private static List<int[]> findSegmentsV3(List<String> lines) {
        int idx = 0;
        List<int[]> res = new ArrayList<>();
        Set<Integer> foundIdx = new HashSet<>();
        while (idx < lines.size()) {
            if(isNumberLine(lines.get(idx))) {
                //a segment started
                int[] seg = new int[3];
                seg[0] = Integer.parseInt(lines.get(idx));
                if(foundIdx.contains(seg[0])) {
                    break;
                }
                foundIdx.add(seg[0]);
                int tmp =idx + 1;
                while (tmp < lines.size() && !isBlankLine(lines.get(tmp))) {
                    tmp++;
                }

                seg[1] = idx + 1;
                seg[2] = tmp - 1;
                res.add(seg);
                idx = tmp;
            } else {
                idx++;
            }
        }
        return res;
    }

    private static boolean isNumberLine(String line) {
        return Character.isDigit(line.charAt(0));
    }

    private static boolean isBlankLine(String line) {
        if(line == null || line.length() < 1) {
            return true;
        }
        char firstCh = line.charAt(0);
        return firstCh != '[' && !Character.isLetterOrDigit(firstCh);
    }

    /**
     * Both idxOfLine and idxOfChar are zero-based.
     * @param lines
     * @param idxOfLine
     * @param idxOfChar
     * @return
     */
    private static char findChar(List<String> lines, int idxOfLine, int idxOfChar, int start) {
        return lines.get(start - idxOfLine).charAt(idxOfChar);
    }

    /**
     *  [0, 0]: idxOfChar, idxOfLine
     * @param line
     * @return
     */
    private static int[] parseIndex(String line) {
        int start = line.indexOf('['), end = line.indexOf(']');
        if(start < 0 || end < 0) {
            // may throw a new exception on production.
        }

        line = line.substring(start + 1, end);
        String[] tokens = line.split(",");
        int[] res = new int[tokens.length];
        for(int i = 0; i < tokens.length; i++) {
            res[i] = Integer.parseInt(tokens[i].trim());
        }
        return res;
    }

    private static List<String> getAllLines(String file) {
        List<String> res = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file), 100);
            String line = br.readLine();
            while (line != null) {
                res.add(line);
                line = br.readLine();
            }
            br.close();  //better to move it to finally block
        } catch (Exception ex) {
            //
        }
        return res;
    }

    public static List<String> getAllLinesV2(String file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(file));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public static List<String> getAllLinesV3(String file) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (Exception ex) {
            //todo
        }
        return lines;
    }

    public static void main(String[] args) {
        System.out.println(parse("/Users/xingfeiyu/tmp/password.txt"));
        System.out.println(parseV2("/Users/xingfeiyu/tmp/password2.txt"));
        System.out.println(parseV3("/Users/xingfeiyu/tmp/password3.txt"));
    }
}
