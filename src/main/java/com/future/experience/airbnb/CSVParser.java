package com.future.experience.airbnb;

/**
 *
  Input: csvformat
 John,Smith,john.smith@gmail.com,Los Angeles,1
 Jane,Roberts,janer@msn.com,"San Francisco, CA",0
 "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1 """Alexandra Alex"""

 Output: escaped string
 John|Smith|john.smith@gmail.com|Los Angeles|1
 Jane|Roberts|janer@msn.com|San Francisco, CA|0
 Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1 "Alexandra Alex"

 * Created by xingfeiy on 6/13/18.
 */
public class CSVParser {
    /**
     * Analyze:
     * - Separator ','
     * - enclose ""
     * - escape " -> ""
     *
     * ---
     * - is not separator -> continue to make term
     * - ',', push current term into stringbuilder
     * - ",  enclose start
     *      - "" -> "
     *      - ", enclose end
     *
     * @param str
     * @return
     */
    public String parse(String str) {
        if(str == null || str.length() < 1) return "";
        int p = 0;
        String curTerm = "";
        StringBuilder sb = new StringBuilder();
        while (p < str.length()) {
            if(str.charAt(p) == '"') {
                //enclose start, and find the end enclose
                int pos = str.indexOf("\"", p + 1);
                while (pos < str.length()) {
                    if(pos < str.length() - 1 && str.charAt(pos + 1) == '"') {
                        pos = str.indexOf("\"", pos + 2);
                    } else {
                        break;
                    }
                }
                //found one
                curTerm = str.substring(p + 1, pos).replaceAll("\"\"", "\"");
                p = pos + 1;
            } else if(str.charAt(p) == ',') {
                sb.append(curTerm).append("|");
                curTerm = "";
                p++;
            } else {
                curTerm += Character.toString(str.charAt(p++));
            }
        }
        sb.append(curTerm);
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        CSVParser p = new CSVParser();
        System.out.println(p.parse("John,Smith,john.smith@gmail.com,Los Angeles,1"));
        System.out.println(p.parse("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0"));
        System.out.println(p.parse("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1 \"\"\"Alexandra Alex\"\"\""));
    }
}
