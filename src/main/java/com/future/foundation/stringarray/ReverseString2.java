package com.future.foundation.stringarray;

/**
 * Given a string, that contains special character together with alphabets (‘a’ to ‘z’ and ‘A’ to ‘Z’), reverse the string in a way that special characters are not affected.

  Examples:

  Input:   str = "a,b$c"
  Output:  str = "c,b$a"
  Note that $ and , are not moved anywhere.
  Only subsequence "abc" is reversed

  Input:   str = "Ab,c,de!$"
  Output:  str = "ed,c,bA!$"

 * Created by someone on 4/26/17.
 */
public class ReverseString2 {

    /**
     * Analyze: It's the question like reverse a string, only difference is there are some special characters.
     *
     * So acutally we can use same solution, double pointers, and if the character is special one, just skip it.
     *
     * @param str
     */
    public static String solution(String str) {
        if(str == null || str.length() < 2) {
            return str;
        }

        char[] chars = str.toCharArray();
        int pointer1 = 0;
        int pointer2 = chars.length - 1;

        while (pointer1 < pointer2) {
            if(!Character.isAlphabetic(chars[pointer1])) {
                pointer1++;
            } else if(!Character.isAlphabetic(chars[pointer2])) {
                pointer2--;
            } else {
                char tmp = chars[pointer1];
                chars[pointer1] = chars[pointer2];
                chars[pointer2] = tmp;
                pointer1++;
                pointer2--;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(solution("a,b$c"));
        System.out.println(solution("Ab,c,de!$"));
    }
}
