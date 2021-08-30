package com.future.experience.instacart;

import java.util.*;

/**
 * 第三轮 coding 公式计算
 *   第一小问 input:["T2", ["T1 = 1", "T2 = T3", "T3 = T1"]] output: T2值   公式都是左边一个变量， 右边是变量或者数值
 *   第二小问 input: ["T2", ["T1 = 1", "T2 = 2 + T4", "T3 = T1 - 4", "T4 = T1 + T3]]  output:T2值
 *      公式左边为变量，右边为一个或多个变量/数值，包括加减操作
 * 第三小问： 在第二小问基础上可能存在解不出的情况
 *    这道题我是用topological sort的方法，但后来发现Top-down 递归就够了
 * 然后发现所有的变量都会有一个相应的赋值
 * 比如这种情况的Testcase就不存在 ["T2", ["T1=4", "T1 = 2 + T2"]]
 */
public class Expression {
    /**
     * Questions:
     * - Does it contains cycle?
     * - Can one variable be assigned multiple times?
     * - The variable has exact one value?
     * @param var
     * @param expList
     * @return
     */
    public static int calculate(String var, String[] expList) {
        Map<String, String> map = new HashMap<>();
        for(String exp : expList) {
            String[] tokens = exp.split("=");
            map.put(tokens[0].trim(), tokens[1].trim());

        }

        return helper(var, map, new HashSet<>());
    }

    private static int helper(String var, Map<String, String> map, Set<String> visited) {
        if(!map.containsKey(var) || visited.contains(var)) {
            return Integer.MIN_VALUE;
        }

        if(isNumber(map.get(var))) {
            return Integer.parseInt(map.get(var));
        }

        visited.add(var);
        return helper(map.get(var), map, visited);
    }

    private static boolean isNumber(String str) {
        for(char ch : str.toCharArray()) {
            if(!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }


    public static int calculateV2(String var, String[] expList) {
        Map<String, String> map = new HashMap<>();
        for(String exp : expList) {
            String[] tokens = exp.split("=");
            map.put(tokens[0].trim(), tokens[1].trim());
            for(String extendExp : extedsExp(exp)) {
                tokens = extendExp.split("=");
                map.put(tokens[0].trim(), tokens[1].trim());
            }
        }
        return helper2(var, map, new HashSet<>());
    }

    private static int helper2(String var, Map<String, String> map, Set<String> visited) {
        if(!map.containsKey(var)) {
            return Integer.MIN_VALUE;
        }

        if(isNumber(var)) {
            return Integer.parseInt(var);
        }

        if(visited.contains(var)) {
            return Integer.MIN_VALUE;
        }

        visited.add(var);

        if(isNumber(map.get(var))) {
            return Integer.parseInt(map.get(var));
        }

        String exp = map.get(var);
        if(exp.indexOf('+') > 0) {
            String[] tokens = splitExp(exp, "\\+");
            return helper2(tokens[0], map, visited) + helper2(tokens[1], map, visited);
        } else if(exp.indexOf('-') > 0) {
            String[] tokens = splitExp(exp, "-");
            return helper2(tokens[0], map, visited) - helper2(tokens[1], map, visited);
        } else {
            return helper2(exp, map, visited);
        }
    }

    private static List<String> extedsExp(String exp) {
        String[] tokens = exp.split("=");
        List<String> res = new ArrayList<>();
        String left = tokens[0].trim(), right = tokens[1].trim();
        if(right.contains("+")) {
            String[] pair = right.split("\\+");
            if(!isNumber(pair[0])) {
                res.add(pair[1].trim() + " = " + left + " - " + pair[0]);
            }

            if(!isNumber(pair[1])) {
                res.add(pair[0].trim() + " = " + left + " - " + pair[1]);
            }
        } else if(right.contains("-")) {
            String[] pair = right.split("-");
            if(!isNumber(pair[0])) {
                res.add(pair[0].trim() + " = " +  left + " + " + pair[1]);
            }

            if(!isNumber(pair[1])) {
                res.add(pair[1].trim() + " = " + pair[0] + " - " + left);
            }
        }

        return res;
    }

    /**
     * 3. followup，expression可能有循环，比如
     * T1=1
     *
     * T2=2
     * T3=T4+T5
     * T4=T5
     * T5=T4
     * 这个情况 返回“IMPOSSIBLE”。
     * @param var
     * @param expList
     * @return
     */
    private static int calculateV3(String var, String[] expList) {
        return 0;
    }

    private static String[] splitExp(String str, String sep) {
        String[] tokens = str.split(sep);
        return new String[]{tokens[0].trim(), tokens[1].trim()};
    }

    public static void main(String[] args) {
//        System.out.println(calculate("T1", new String[]{"T5 = 1","T1 = T2", "T2 = T3", "T3 = T4", "T4 = T2", "T4 = T5", "T4 = T6"}));
//        System.out.println(calculate("T2", new String[]{"T1 = 1", "T2 = T3", "T3 = T1"}));
//        System.out.println(calculate("T2", new String[]{"T1 = 1", "T2 = T3", "T3 = T1", "T2 = T1"}));
//        System.out.println(calculate("T2", new String[]{"T1 = 1", "T2 = T3", "T3 = T2"}));
//        System.out.println(calculateV2("T2", new String[]{"T1 = 1", "T2 = 2 + T4", "T3 = T1 - 4", "T4 = T1 + T3"}));
        System.out.println(calculateV2("T2", new String[]{"T1 = 1 + T2", "T1 = 1 - T2"}));
        System.out.println(calculateV2("T2", new String[]{"T1 = 4", "T1 = 2 + T2"}));
    }
}
