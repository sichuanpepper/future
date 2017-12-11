package com.future.round2;

import java.util.Stack;

/**
 *
 Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"

 * Created by xingfeiy on 12/10/17.
 */
public class Problem71 {
    /**
     * Analyze:
     * We can use a stack to store each operation.
     * ./ -> do nothing
     * /a -> push into stack
     * ../ -> pop out stack.
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if(path == null || path.length() < 1) return path;
        Stack<String> stack = new Stack<>();
        for(String command : path.split("/")) {
            if(command.equals(".") || command.equals("")) {
                continue;
            } else if(command.equals("..")) {
                if(!stack.isEmpty()) stack.pop();
            } else {
                stack.push(command);
            }
        }

        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res == "" ? "/" : res;
    }

    public static void main(String[] args) {
        Problem71 p = new Problem71();
        System.out.println(p.simplifyPath(""));
        System.out.println(p.simplifyPath("/"));
        System.out.println(p.simplifyPath("/home/"));
        System.out.println(p.simplifyPath("/home/../home/../"));
        System.out.println(p.simplifyPath("/a/./b/../../c/"));
    }
}
