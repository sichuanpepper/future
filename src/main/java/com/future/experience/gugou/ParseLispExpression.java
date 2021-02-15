package com.future.experience.gugou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/parse-lisp-expression/
 *
 * Implement a function 'int eval(String expr)' that evaluates expressions matching this syntax:
 * expr ::= int | '(' op expr+ ')'
 * op ::= '+' | '*'
 * 实例：
 *   `3` => 3
 * `( + 1 2 )` => 3
 * `( * 3 ( + 4 1 2 ) )` => 21
 * ( 3 )  => invalid
 *
 */
public class ParseLispExpression {
    public int evaluate(String expression) {
        return eval(expression, new HashMap<>());
    }

    private int eval(String expression, Map<String, Integer> variables) {
        //integer, variable, expression
        if(expression.charAt(0) != '(') {
            //an integer, it could be a negative.
            if(Character.isDigit(expression.charAt(0)) || expression.charAt(0) == '-') {
                return Integer.parseInt(expression);
            } else {
                return variables.get(expression);
            }
        }

        //check sign
        char s = expression.charAt(1);
        List<String> tokens = parseStr(expression.substring(expression.indexOf(' ') + 1, expression.length() - 1).trim());
        if(s == 'a') { // takes 2 paras
            return eval(tokens.get(0), variables) + eval(tokens.get(1), variables);
        } else if(s == 'm') {
            return eval(tokens.get(0), variables) * eval(tokens.get(1), variables);
        } else { // let takes 2*n + 1 paras
            Map<String, Integer> internal = new HashMap<>();
            internal.putAll(variables);
            for(int i = 0; i < tokens.size() - 2; i += 2) {
                internal.put(tokens.get(i), eval(tokens.get(i + 1), internal));
            }
            return eval(tokens.get(tokens.size() - 1), internal);
        }

    }

    private List<String> parseStr(String str) {
        if(str == null || str.length() < 1) {
            return new ArrayList<>();
        }
        int start = 0, p = 0, cntP = 0;
        List<String> tokens = new ArrayList<>();
        while(p < str.length()) {
            if(str.charAt(p) == '(') {
                cntP++;
            } else if(str.charAt(p) == ')') {
                cntP--;
            }

            if(str.charAt(p) == ' ' && cntP == 0) {
                tokens.add(str.substring(start, p).trim());
                start = ++p;
            } else {
                p++;
            }

        }
        tokens.add(str.substring(start, p));
        return tokens;
    }

    public static void main(String[] args) {
        ParseLispExpression p = new ParseLispExpression();

        System.out.println(p.evaluate("(add 1 2)"));
    }
}
