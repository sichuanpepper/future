package com.future.experience.aibiying;

import java.util.Stack;

/**
 * Created by xingfeiy on 6/26/18.
 */
public class MiniParse {
//    public NestedInteger deserialize(String s) {
//        if (s.isEmpty())
//            return null;
//        if (s.charAt(0) != '[') // ERROR: special case
//            return new NestedInteger(Integer.valueOf(s));
//
//        Stack<NestedInteger> stack = new Stack<>();
//        NestedInteger curr = null;
//        int l = 0; // l shall point to the start of a number substring;
//        // r shall point to the end+1 of a number substring
//        for (int r = 0; r < s.length(); r++) {
//            char ch = s.charAt(r);
//            if (ch == '[') {
//                if (curr != null) {
//                    stack.push(curr);
//                }
//                curr = new NestedInteger();
//                l = r+1;
//            } else if (ch == ']') {
//                String num = s.substring(l, r);
//                if (!num.isEmpty())
//                    curr.add(new NestedInteger(Integer.valueOf(num)));
//                if (!stack.isEmpty()) {
//                    NestedInteger pop = stack.pop();
//                    pop.add(curr);
//                    curr = pop;
//                }
//                l = r+1;
//            } else if (ch == ',') {
//                if (s.charAt(r-1) != ']') {
//                    String num = s.substring(l, r);
//                    curr.add(new NestedInteger(Integer.valueOf(num)));
//                }
//                l = r+1;
//            }
//        }
//
//        return curr;
//    }


//    public NestedInteger deserialize(String s) {
//        NestedInteger ret = new NestedInteger();
//        if (s == null || s.length() == 0) return ret;
//        if (s.charAt(0) != '[') {
//            ret.setInteger(Integer.parseInt(s));
//        }
//        else if (s.length() > 2) {
//            int start = 1, count = 0;
//            for (int i = 1; i < s.length(); i++) {
//                char c = s.charAt(i);
//                if (count == 0 && (c == ',' || i == s.length() - 1)) {
//                    ret.add(deserialize(s.substring(start, i)));
//                    start = i + 1;
//                }
//                else if (c == '[') count++;
//                else if (c == ']') count--;
//            }
//        }
//        return ret;
//    }
}
