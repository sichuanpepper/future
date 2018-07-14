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
//    public NestedInteger deserialize(String s) {
//        if(s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s));
//        int p = 0, sign = 1;
//        NestedInteger curNI = null;
//        Stack<NestedInteger> stack = new Stack<>();
//        while (p < s.length()) {
//            if(s.charAt(p) == '[') {
//                //push into stack
//                if(curNI != null) stack.push(curNI);
//                curNI = new NestedInteger();
//                sign = 1;
//                p++;
//            } else if(s.charAt(p) == '-') {
//                sign = -1;
//                p++;
//            } else if(s.charAt(p) == ',') {
//                p++;
//            } else if(Character.isDigit(s.charAt(p))){
//                //digit
//                int start = p;
//                while (Character.isDigit(s.charAt(p))) p++;
//                curNI = new NestedInteger(sign * Integer.parseInt(s.substring(start, p)));
//            } else if(s.charAt(p) == ']') {
//                if(!stack.isEmpty()) {
//                    NestedInteger tmp = stack.pop();
//                    tmp.add(curNI);
//                    curNI = tmp;
//                }
//                p++;
//            }
//        }
//        return curNI == null ? new NestedInteger() : curNI;
//
//    }

    /**
     * Analyze:
     * We can just go through characters one by one.
     * - If string start with digit
     * - Otherwise
     *      - '[' create an NestedInteger obj, push current object into stack, current pointer -> new obj
     *      - ',', ignore
     *      - '-' sign
     *      - digit, find complete number, and set the value into current obj.
     *      - ']' a complete obj found, check if there's a parent obj in stack.
     * "123" -> an object, string doesn't start with '[', single object.
     * "[]" -> an empty object
     * "[[]]" -> an object which contains an empty object.
     * [123, 456] -> an object which contains two numbers 123 and 456
     * @param s
     * @return
     */
    public NestedInteger deserialize(String s) {
        if(s == null || s.length() < 1) return null;
        //here we can't check if first character is digit, likes case "-3".
        if(s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s));
        //don't init cur as null, think the difference between "123" and "[123]"
        NestedInteger cur = new NestedInteger();
        Stack<NestedInteger> stack = new Stack<>();
        int p = 0, sign = 1;
        //don't forgot move pointer...
        while (p < s.length()) {
            if(s.charAt(p) == ',') {
                p++;
            } else if(s.charAt(p)  == '-') {
                sign = -1;
                p++;
            } else if(Character.isDigit(s.charAt(p))) {
                int start = p;
                while (p < s.length() && Character.isDigit(s.charAt(p))) p++;
                cur.setInteger(sign * Integer.parseInt(s.substring(start, p)));
                sign = 1;
            } else if(s.charAt(p) == '[') {
                if(cur != null) stack.push(cur);
                cur = s.charAt(p + 1) == ']' ? null : new NestedInteger();
                p++;
            } else if(s.charAt(p) == ']') {
                if(!stack.isEmpty()) {
                    NestedInteger tmp = stack.pop();
                    if(cur != null) tmp.add(cur);
                    cur = tmp;
                }
                p++;
            }
        }
        return cur;
    }

    public static void main(String[] args) {
        // null -> null
        // [] -> empty NestedInteger
        // [] ->
    }

}
