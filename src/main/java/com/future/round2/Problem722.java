package com.future.round2;

import java.util.ArrayList;
import java.util.List;

public class Problem722 {
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        boolean isBlock = false;
        String prefix = "", suffix = "";
        for(String line : source) {
            int indexOfDS = line.indexOf("//");
            int indexOfBlo = line.indexOf("\\*");
            if(isBlock) {
                int indexOfBloEnd = line.indexOf("*/");
                if(indexOfBloEnd >= 0) {
                    suffix = line.substring(indexOfBloEnd + 1, line.length());
                    String newLine = prefix + suffix;
                    if(newLine.length() > 0) {
                        res.add(line);
                    }
                    prefix = "";
                    suffix = "";
                    isBlock = false;
                }
            } else if(indexOfDS < 0 && indexOfBlo < 0) {
                res.add(line);
            } else {
                String newLine = line.substring(0, Math.min(indexOfDS, indexOfBlo));
                isBlock = indexOfBlo >= 0 && (indexOfBlo < indexOfDS || indexOfDS < 0);
                if(isBlock) {
                    int indexOfBloEnd = line.indexOf("*/");
                    if(indexOfBloEnd > indexOfBlo) {
                        res.add(newLine + line.substring(indexOfBloEnd + 1, line.length()));
                    } else {
                        prefix = newLine;
                    }
                    isBlock = true;
                } else {
                    if(newLine.length() > 0) res.add(newLine);
                }
            }
        }
        return res;
    }
}
