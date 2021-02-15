package com.future.experience.fsbk;

public class ValidParenthesis {
    public String validParenthesis(String str) {
        if(str == null || str.length() < 1) return str;
        int left = 0, right = 0;
        StringBuilder sb = new StringBuilder();
        for(char ch : str.toCharArray()) {
            if(ch == '(') {
                sb.append("(");
                left++;
            } else {
                if(left > 0) {
                    sb.append(")");
                    left--;
                } else {
                    sb.append("()");
                }
            }
        }
        while (left-- > 0) sb.append(")");
        return sb.toString();
    }


    public static void main(String[] args) {
        ValidParenthesis p = new ValidParenthesis();
        System.out.println(p.validParenthesis("((("));
        System.out.println(p.validParenthesis("(((()"));
        System.out.println(p.validParenthesis("(()("));
        System.out.println(p.validParenthesis(")))())"));
    }
}
