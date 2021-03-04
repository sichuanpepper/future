package com.future.experience.fsbk;

import java.util.Stack;

public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        char preOperator = '+';
        while(p < s.length()) {
            //corner cases: contains ' '
            if(s.charAt(p) == '+' || s.charAt(p) == '-' || s.charAt(p) == '/' || s.charAt(p) == '*') {
                preOperator = s.charAt(p++);
            } else if(Character.isDigit(s.charAt(p))) {
                int p2 = p;
                while(p2 < s.length() && Character.isDigit(s.charAt(p2))) {
                    p2++;
                }
                int num = Integer.parseInt(s.substring(p, p2));
                if(preOperator == '+') {
                    stack.push(num);
                } else if(preOperator == '-') {
                    stack.push(-num);
                } else if(preOperator == '*') {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
                p = p2;
            } else {
                p++;
            }
        }

        int res = 0;
        for(int n : stack) {
            res += n;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculatorII().calculate(" 3/2 "));
    }
}
