package com.future.experience.gugou;

/**
 * 给一种编码方式： Hi -> 2Hi   hello -> 5hello, helloworld -> 5hello5world, 也就是变成一个数字，然后跟这个数字这么多字符在后面。那么下面的几个例子就是编码不正确的：
 * 2hello, 5hello5world!, 109something。前两种是后面的字符个数不对，109something 是因为有两种解析方法：1） 1跟个0，9跟个something。2）10跟个"9something”。
 * 问：给你一个编码后的字符串，请返回这个编码是否正确。
 *
 * 1. the first character must be digit
 * 2. Is "0" valid? or "0000" valid?
 *
 * A straightforward way, do it in recursive way, for each number we found,
 *  1, check if there still have enough characters remained after the number.
 *  2, If yes, check the remaining sub string recursively.
 */
public class EncodingValidation {
    public boolean isValid(String str) {
        if(str == null || str.length() < 1) return true;
        if(!Character.isDigit(str.charAt(0))) return false;
        if(str.charAt(0) == '0') {
            return isValid(str.substring(1));
        }
        int p1 = 0, p2 = 0;
        while (p2 < str.length() && Character.isDigit(str.charAt(p2))) {
            int num = Integer.parseInt(str.substring(p1, p2 + 1));
            if(p2 + num > str.length()) return false;
            if(isValid(str.substring(p2 + num + 1))) return true;
            p2++;
        }
        return false;
    }

    //todo, better solution??? It's not a correct solution yet.
    public boolean isValidDP(String str) {
        if(str == null || str.length() < 1) return true;
        if(!Character.isDigit(str.charAt(0))) return false;
        boolean[] dp = new boolean[str.length() + 1];
        dp[str.length()] = true;
        int p = str.length() - 1;
        while (p >= 0) {
            if(Character.isDigit(p)) {
                int p1 = p;
                while (p1 >= 0 && Character.isDigit(str.charAt(p1))) p1--;
                for(int i = p; i > p1; i--) {
                    for(int j = i; j > p1; j--) {
                        if(str.charAt(j) == '0') continue;
                        int num = Integer.parseInt(str.substring(j, i + 1));
                        dp[j] = (j + num + 1) < dp.length ? dp[j + num + 1] : false;
                    }
                }
                p = p1;
            } else {
                p--;
            }

        }
        return dp[0];
    }

    public static void main(String[] args) {
        EncodingValidation p = new EncodingValidation();
        System.out.println(p.isValid("109something"));
        System.out.println(p.isValid("119something"));
        System.out.println(p.isValid("5hello5world"));
        System.out.println(p.isValid("5hello5world!"));
        System.out.println("DP>>>>>>>>>>>>>");
        System.out.println(p.isValidDP("109something"));
        System.out.println(p.isValidDP("119something"));
        System.out.println(p.isValidDP("5hello5world"));
        System.out.println(p.isValidDP("5hello5world!"));
    }
}
