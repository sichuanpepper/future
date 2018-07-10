package com.future.experience.aibiying;

import java.util.Stack;

/**
 Given a nested list of integers represented as a string, implement a parser to deserialize it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Note: You may assume that the string is well-formed:

 String is non-empty.
 String does not contain white spaces.
 String contains only digits 0-9, [, - ,, ].
 Example 1:

 Given s = "324",

 You should return a NestedInteger object which contains a single integer 324.
 Example 2:

 Given s = "[123,[456,[789]]]",

 Return a NestedInteger object containing a nested list with 2 elements:

 1. An integer containing value 123.
 2. A nested list containing two elements:
 i.  An integer containing value 456.
 ii. A nested list with one element:
 a. An integer containing value 789.

 * Created by xingfeiy on 6/26/18.
 */
public class MiniParse {
    public NestedInteger deserialize(String s) {
        if(s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s));
        int p = 0, sign = 1;
        NestedInteger curNI = null;
        Stack<NestedInteger> stack = new Stack<>();
        while (p < s.length()) {
            if(s.charAt(p) == '[') {
                //push into stack
                if(curNI != null) stack.push(curNI);
                curNI = null;
                sign = 1;
                p++;
            } else if(s.charAt(p) == '-') {
                sign = -1;
                p++;
            } else if(s.charAt(p) == ',') {
                p++;
            } else if(Character.isDigit(s.charAt(p))){
                //digit
                int start = p;
                while (Character.isDigit(s.charAt(p))) p++;
                curNI = new NestedInteger(sign * Integer.parseInt(s.substring(start, p)));
            } else if(s.charAt(p) == ']') {
                if(!stack.isEmpty()) {
                    NestedInteger tmp = stack.pop();
                    tmp.add(curNI);
                    curNI = tmp;
                }
                p++;
            }
        }
        return curNI == null ? new NestedInteger() : curNI;

    }

}
