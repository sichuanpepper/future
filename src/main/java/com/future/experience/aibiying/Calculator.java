package com.future.experience.aibiying;

import java.util.Stack;

/**
 * Created by xingfeiy on 7/4/18.
 */
public class Calculator {
    /**
     * Analyze:
     * - use stack
     * - '*' and '/' have high priority, so push '+' and '-' into stack
     * - meets '*' or '/', pop one from stack for computing, and then push the result into stack.
     *
     * - ' ', continue
     * - operator
     *  - pre-sign '+' or '-' , pre-sign * curVal -> stack
     *  - pre-sign '*' or '/', stack.pop() * or / curVal -> stack
     *  - pre-sign <- cur-sign
     * @param s
     * @return
     */
    public int calculate(String s) {
        if(s == null || s.length() < 1) return 0;
        char preSign = '+';
        long curVal = 0, res = 0;
        int p = 0;
        Stack<Long> stack = new Stack<>();
        while (p < s.length()) {
            if(s.charAt(p) == ' ') {
                p++;
            } else if(!Character.isDigit(s.charAt(p))){ //operator
                addToStack(stack, preSign, curVal);
                curVal = 0;
                preSign = s.charAt(p++);
            } else if(Character.isDigit(s.charAt(p))) {
                int tmp = p;
                while (p < s.length() && Character.isDigit(s.charAt(p))) p++;
                curVal = Integer.parseInt(s.substring(tmp, p));
            }
        }
        //
        if(curVal > 0) addToStack(stack, preSign, curVal);
        while (!stack.isEmpty()) res += stack.pop();
        return (int)res;
    }

    private void addToStack(Stack<Long> stack, char preSign, long val) {
        if(preSign == '+') {
            stack.push(val);
        } else if(preSign == '-') {
            stack.push(-val);
        } else if(preSign == '*') {
            stack.push(stack.pop() * val);
        } else {
            stack.push(stack.pop() / val);
        }
    }


    /**
     * Implement a basic calculator to evaluate a simple expression string.

     The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
     non-negative integers and empty spaces .
     "(1+(4+5+2)-3)+(6+8)"
     "3+1+(2-3)"
     - digits, find number
     - ' ' continue
     - '+' or '-'
     - '(' push
     - ')'
     * @param s
     * @return
     */
    public int calculate2(String s) {
        if(s == null || s.length() < 1) return 0;
        int curVal = 0, sign = 1, p = 0;
        Stack<Integer> stack = new Stack<>();
        while (p < s.length()) {
            if(s.charAt(p) == ' ') {
                p++;
            } else if(Character.isDigit(s.charAt(p))) {
                int start = p;
                //don't forgot p < s.length();
                while (p < s.length() && Character.isDigit(s.charAt(p))) p++;
                curVal += sign * Integer.parseInt(s.substring(start, p));
            } else if(s.charAt(p) == '+') {
                sign = 1;
                p++;
            } else if(s.charAt(p) == '-') {
                sign = -1;
                p++;
            } else if(s.charAt(p) == '(') {
                stack.push(curVal);
                stack.push(sign);
                curVal = 0;
                sign = 1;
                p++;
            } else if(s.charAt(p) == ')') {
                curVal = stack.pop() * curVal + stack.pop();
                p++;
            }
        }
        return curVal;
    }


    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.calculate("3/2 "));

        System.out.println(c.calculate2("3+1+(2-3)"));
        System.out.println(c.calculate2("3+1-(2-3)"));
        System.out.println(c.calculate2("(3+1-(2-3))"));
        System.out.println(c.calculate2("(3+1)-(2-3)"));
        System.out.println(c.calculate2("(3+1)-(-2-3)+1 "));
    }
}
