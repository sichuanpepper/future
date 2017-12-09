package com.future.round2;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

 * Created by someone on 10/11/17.
 */
public class Problem6 {
    /**
     * Analyze: let's draw down row 4.
     * P      I      N
     * A    L S    I G
     * Y  A   H  R
     * P      I
     *
     * First of all, a V shape includes rows * 2 - 1 nodes, likes the below one, which includes 2 * 4 - 1 nodes.
     * P      I
     * A    L
     * Y  A
     * P
     * In the first row, the distance between P and I is the node of below V shape(AYPAL), which is 5
     * In the second row, the distance between A and L is the node of below V, which is 2 * 2 - 1,
     * and the upper V is 1 * 2 - 1
     * So for each row, below V, upper V, below V, upper V....
     * start_index + (total_rows - current_row) * 2, then + (current_row - 1) * 2
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
        for(int row = 1; row <= numRows; row++) {
            int index = row - 1;
            boolean downward = true;

            while (index < s.length()) {
                if(row == 1 && !downward) {
                    downward = true;
                    continue;
                }
                if(row == numRows && downward) {
                    downward = false;
                    continue;
                }
                sb.append(s.charAt(index));
                if(downward) {
                    index += (numRows - row) * 2;
                    downward = false;
                } else {
                    index += (row - 1) * 2;
                    downward = true;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("abc", 4));
        System.out.println(convert("a", 1));
        System.out.println(convert("PAYPALISHIRING", 3)); //PAHNAPLSIIGYIR
        System.out.println(convert("PAYPALISHIRING", 4));
    }

}
